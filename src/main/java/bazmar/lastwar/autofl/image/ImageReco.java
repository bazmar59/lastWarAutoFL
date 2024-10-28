package bazmar.lastwar.autofl.image;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;

import bazmar.lastwar.autofl.data.Constants;
import bazmar.lastwar.autofl.data.Coord;
import ch.qos.logback.classic.Logger;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;

public class ImageReco {

	private static Logger logger = (Logger) LoggerFactory.getLogger("ImageReco");
	private static Logger loggerTime = (Logger) LoggerFactory.getLogger("LoggerTime");

	public static Coord findFirst(Image bigImage, List<Image> smallImages) {
		return findFirst(bigImage, smallImages, Constants.SIMILARITY_THRESHOLD);
	}

	public static Coord findFirst(Image bigImage, List<Image> smallImages, int threshold) {
		for (Image current : smallImages) {
			Coord tmp = findFirst(bigImage, current, threshold);
			if (tmp != null) {
				logger.debug("findFirst %s found".formatted(current.getName()));
				return tmp;
			} else {
				logger.debug("findFirst %s not found".formatted(current.getName()));
			}
		}
		return null;
	}

	public static Coord findFirst(Image bigImage, Image smallImage) {
		return findFirst(bigImage, smallImage, Constants.SIMILARITY_THRESHOLD);
	}

	public static Coord findFirst(Image bigImage, Image smallImage, int threshold) {
		List<Coord> coords = findMultiple(bigImage, smallImage, threshold, 1);
		if (coords != null && coords.size() > 0) {
			return coords.get(0);
		}
		return null;
	}

	private static List<Coord> findMultiple(Image bigImage, Image smallImage, int threshold, int limit) {
		long startTime = System.currentTimeMillis();
		int found = 0;
		List<Coord> coords = new ArrayList<Coord>();
		Coord coord = null;
		try {

			PixelReader readerSmall = smallImage.getPixelReader();
			PixelReader readerBig = bigImage.getPixelReader();

			int startX = 0;
			int startY = 0;
			int endXBig = (int) bigImage.getWidth();
			int endYBig = (int) bigImage.getHeight();

			int searchWidth = (int) smallImage.getWidth();
			int searchHeight = (int) smallImage.getHeight();

			// Loop on big
			for (int y = startY; y <= endYBig - searchHeight; y++) {
				for (int x = startX; x <= endXBig - searchWidth; x++) {
					boolean match = true;
					smallImageLoop: for (int dy = 0; dy < searchHeight; dy++) {
						for (int dx = 0; dx < searchWidth; dx++) {
							Color colorSmall = readerSmall.getColor(dx, dy);
							if (colorSmall.getOpacity() < 1) {
								continue;
							}
							Color colorBig = readerBig.getColor(x + dx, y + dy);
							if (colorBig.getOpacity() < 1) {
								continue;
							}
							if (!ColorSimilar.areColorsSimilar(colorSmall, colorBig, threshold)) {
								match = false;
								break smallImageLoop;
							}
						}
					}

					if (match) {
						loggerTime.debug("found {} (x={}, y={}) in {}ms", smallImage.getName(), x, y,
								System.currentTimeMillis() - startTime);
						coord = new Coord(x, y, searchHeight, searchWidth, smallImage.getName());
						coords.add(coord);
						found++;

						if (found >= limit) {
							return coords;
						}

						x = x + searchWidth;
					}
				}
			}

		} catch (Exception e) {
			logger.debug(e.getMessage());
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis() - startTime;
		if (endTime > 50) {
			loggerTime.error("{} found {} times with limit {} in {}ms", smallImage.getName(), coords.size(), limit,
					endTime);
		} else {
			if (endTime > 20) {
				loggerTime.info("{} found {} times with limit {} in {}ms", smallImage.getName(), coords.size(), limit,
						endTime);
			} else {
				loggerTime.debug("{} found {} times with limit {} in {}ms", smallImage.getName(), coords.size(), limit,
						endTime);
			}
		}

		return coords;
	}

}
