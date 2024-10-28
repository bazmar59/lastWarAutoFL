package bazmar.lastwar.autofl.io;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.slf4j.LoggerFactory;

import bazmar.lastwar.autofl.data.BotType;
import bazmar.lastwar.autofl.data.Constants;
import bazmar.lastwar.autofl.data.Context;
import bazmar.lastwar.autofl.data.Zone;
import bazmar.lastwar.autofl.exception.ScreenIndexException;
import bazmar.lastwar.autofl.image.Image;
import bazmar.lastwar.autofl.utils.Utils;
import ch.qos.logback.classic.Logger;

public class Screen {

	private static Logger logger = (Logger) LoggerFactory.getLogger("Screen");

	private int origX = 0;
	private int origY = 0;
	private int width = 0;
	private int height = 0;
	private int screenIndex = 0;
	private BotType botType;

	private static Screen instanceScreen1 = null;
	private static Screen instanceScreen2 = null;
	private static Screen instanceFl = null;

	private Screen(Context context) {
		origX = context.origX();
		origY = context.origY();
		width = context.width();
		height = context.height();
		screenIndex = context.screenIndex();
		botType = context.botType();
	}

	static Screen getInstance(BotType botType) {
		if (botType.equals(BotType.SCREEN1))
			return instanceScreen1;
		if (botType.equals(BotType.SCREEN2))
			return instanceScreen2;
		return instanceFl;
	}

	public static Screen getInstance(Context context) {
		logger.debug("Screen get instance with Context %s".formatted(context));

		if (BotType.FL.equals(context.botType())) {
			if (instanceFl == null) {
				instanceFl = new Screen(context);
			}
			return instanceFl;
		}

		if (BotType.SCREEN1.equals(context.botType())) {
			if (instanceScreen1 == null) {
				instanceScreen1 = new Screen(context);
			}
			return instanceScreen1;
		}

		if (BotType.SCREEN2.equals(context.botType())) {
			if (instanceScreen2 == null) {
				instanceScreen2 = new Screen(context);
			}
			return instanceScreen2;
		}
		return null;
	}

	public static Screen createInstance(Context context) {
		logger.debug("Screen create instance with Context %s".formatted(context));

		if (BotType.FL.equals(context.botType())) {
			instanceFl = new Screen(context);
			return instanceFl;
		}

		if (BotType.SCREEN1.equals(context.botType())) {
			instanceScreen1 = new Screen(context);
			return instanceScreen1;
		}

		if (BotType.SCREEN2.equals(context.botType())) {
			instanceScreen2 = new Screen(context);
			return instanceScreen2;
		}
		return null;
	}

	public void screen(String path, int x, int y, int width, int height) {
		screen(path, x, y, width, height, screenIndex);
	}

	public void screen(String path, int x, int y, int width, int height, int screenIndex) {
		try {
			// Get the default screen device
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			GraphicsDevice[] screens = ge.getScreenDevices();

			if (screenIndex >= 0 && screenIndex < screens.length) {
				// Get the screen bounds
				Rectangle screenBounds = screens[screenIndex].getDefaultConfiguration().getBounds();

				// Create Rectangle for the specified region
				Rectangle captureBounds = new Rectangle(screenBounds.x + x, screenBounds.y + y, width, height);

				// Capture the screenshot
				Robot robot = new Robot();
				BufferedImage screenshot = robot.createScreenCapture(captureBounds);

				// Save the screenshot to a file
				File outputFile = new File(path);

				ImageIO.write(screenshot, "png", outputFile);

				logger.debug("Screenshot to %s with screenIndex %s".formatted(path, screenIndex));
			} else {
				logger.error("Invalid screen index.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Image screenMemory(int x, int y, int width, int height, boolean debug, String name) {
		return screenMemory(x, y, width, height, screenIndex, debug, name);
	}

	public Image screenMemory(int x, int y, int width, int height, boolean debug) {
		return screenMemory(x, y, width, height, screenIndex, debug, null);
	}

	public Image screenMemory(Zone zone) {
		return screenMemory(zone, false, null);
	}

	public Image screenMemory(Zone zone, boolean debug) {
		return screenMemory(zone, debug, null);
	}

	public Image screenMemory(Zone zone, boolean debug, String name) {
		if (zone.isInGame()) {
			return screenMemoryWithOrigin(zone.getX(), zone.getY(), zone.getWidth(), zone.getHeight(),
					zone.getScreenIndex(), debug, name);
		} else {
			return screenMemory(zone.getX(), zone.getY(), zone.getWidth(), zone.getHeight(), zone.getScreenIndex(),
					debug, name);
		}
	}

	public Image screenMemoryWithOrigin(int x, int y, int width, int height, int screenIndex, boolean debug,
			String name) {
		return screenMemory(x + origX, y + origY, width, height, screenIndex, debug, name);
	}

	public Image screenMemory(int x, int y, int width, int height, int screenIndex, boolean debug, String name) {
		Image out = null;
		try {

			// Get the default screen device
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			GraphicsDevice[] screens = ge.getScreenDevices();

			if (screenIndex >= 0 && screenIndex < screens.length) {
				// Get the screen bounds
				Rectangle screenBounds = screens[screenIndex].getDefaultConfiguration().getBounds();

				int effectiveX = screenBounds.x + x;
				int effectiveY = screenBounds.y + y;
				// Create Rectangle for the specified region
				Rectangle captureBounds = new Rectangle(effectiveX, effectiveY, width, height);

				// Capture the screenshot
				Robot robot = new Robot();

				BufferedImage buffImg = robot.createScreenCapture(captureBounds);
				out = Utils.bufferedImageToImage(buffImg);
				if (debug) {
					String path = Constants.DEBUG_SCREEN_PATH + botType + "-"
							+ (name != null ? name : "debugScreenMemory") + "-" + Utils.readableDate() + ".png";
					logger.debug("screenDebug to %s with screenIndex=%s effectiveX=%s effectiveY=%s".formatted(path,
							screenIndex, effectiveX, effectiveY));
					File outputFile = new File(path);
					ImageIO.write(buffImg, "png", outputFile);
				}

			} else {
				logger.error("Invalid screen index {}", screenIndex);
				throw new ScreenIndexException("Invalid screen index " + screenIndex);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return out;

	}

	public void screenDebug(String name) {
		String path = Constants.DEBUG_SCREEN_PATH + name + "-" + botType + "-" + Utils.readableDate() + ".png";
		logger.debug("screenDebug to {}", path);
		screen(path, origX, origY, width + 1, height + 1);
	}

	public void saveKick(Image image, String name) {
		String path = Constants.KICK_SCREEN_PATH + Utils.readableDateMs() + "-" + name + ".png";
		logger.info("saveKick to %s".formatted(path));
		File outputFile = new File(path);
		try {
			ImageIO.write(Utils.convertToBufferedImage(image), "png", outputFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getOrigX() {
		return origX;
	}

	public void setOrigX(int origX) {
		this.origX = origX;
	}

	@Override
	public String toString() {
		return "Screen [origX=" + origX + ", origY=" + origY + ", width=" + width + ", height=" + height
				+ ", screenIndex=" + screenIndex + ", botType=" + botType + "]";
	}

}
