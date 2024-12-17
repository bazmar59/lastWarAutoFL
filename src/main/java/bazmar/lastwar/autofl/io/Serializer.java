package bazmar.lastwar.autofl.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.slf4j.LoggerFactory;

import bazmar.lastwar.autofl.data.Constants;
import bazmar.lastwar.autofl.data.Stats;
import ch.qos.logback.classic.Logger;

public class Serializer {

	private static Logger logger = (Logger) LoggerFactory.getLogger("Serializer");

	public static void saveStats(Stats stats) {
		try (FileOutputStream fileOut = new FileOutputStream(Constants.STATS_PATH);
				ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {

			objectOut.writeObject(stats);
			logger.info("Stats saved to %s".formatted(Constants.STATS_PATH));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Stats loadStats() {
		try (FileInputStream fileIn = new FileInputStream(Constants.STATS_PATH);
				ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {

			Stats stats = (Stats) objectIn.readObject();
			logger.info("Stats load from %s".formatted(Constants.STATS_PATH));
			return stats;

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return new Stats();
		}
	}
}