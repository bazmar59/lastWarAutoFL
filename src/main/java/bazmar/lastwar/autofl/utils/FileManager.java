package bazmar.lastwar.autofl.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;

import bazmar.lastwar.autofl.image.Image;
import ch.qos.logback.classic.Logger;

public class FileManager {

	private static Logger logger = (Logger) LoggerFactory.getLogger("FileManager");

	public static void createDirectoryIfNotExists(String relativePath) {
		File directory = new File(relativePath);

		if (!directory.exists()) {
			boolean created = directory.mkdirs();
			if (created) {
				logger.info("directory {} created", relativePath);
			} else {
				logger.error("Error during directory {} creation", relativePath);
			}
		} else {
			logger.info("directory {} already exists", relativePath);
		}
	}

	public static void deleteDirectoryContents(Path directory) {
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(directory)) {
			for (Path entry : stream) {
				BasicFileAttributes attrs = Files.readAttributes(entry, BasicFileAttributes.class);
				if (attrs.isDirectory()) {
					deleteDirectoryContents(entry);
				}
				Files.delete(entry);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Image loadImage(String path) {
		Image image = null;
		String overridedPath = "." + path;

		File file = new File(overridedPath);
		if (file.exists()) {
			try {
				image = new Image(new FileInputStream(file), overridedPath);
			} catch (FileNotFoundException e) {
				logger.info("loadImage resource {} not found", overridedPath);
			}
		}

		if (image != null) {
			logger.info("loadImage resource {} loaded", overridedPath);
		} else {
			image = loadImageFromJar(path);
		}
		return image;
	}

	public static Image loadImageFromJar(String path) {
		Image image = null;

		try (InputStream is = FileManager.class.getResourceAsStream(path)) {
			if (is != null) {
				image = new Image(is, path);
			} else {
				logger.error("loadImageFromJar resource {} not found", path);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (image != null) {
			logger.info("loadImageFromJar resource {} loaded", path);
		}
		return image;
	}

	public static String readFile(String fileName) {
		InputStream inputStream = FileManager.class.getClassLoader().getResourceAsStream(fileName);

		if (inputStream == null) {
			logger.error("File " + fileName + " doesn't exists");
		}

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
			return reader.lines().collect(Collectors.joining(System.lineSeparator()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
}
