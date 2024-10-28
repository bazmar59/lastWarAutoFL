package bazmar.lastwar.autofl.utils;

import java.io.IOException;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;

public class ProcessManager {
	private static Logger logger = (Logger) LoggerFactory.getLogger("ProcessManager");
	private static String BLUESTACK_PROCESS_NAME = "HD-Player.exe";
	private static String BLUESTACK_X_PROCESS_NAME = "BlueStacks X.exe";
	// Change it according to your workstation
	private static String BOT_FL_PROCESS = "C:\\Program Files\\BlueStacks_nxt\\HD-Player.exe\" --instance Pie64_4 --cmd launchAppWithBsx --package \"com.fun.lastwar.gp\" --source desktop_shortcut";

	public static void startBotFL() {
		startProcess(BOT_FL_PROCESS);
	}

	public static void killBluestackX() {
		killProcess(BLUESTACK_X_PROCESS_NAME);
		killProcess(BLUESTACK_PROCESS_NAME);
	}

	private static void killProcess(String processName) {
		String command = "taskkill /F /IM \"" + processName + "\"";

		try {
			ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", command);
			Process process = processBuilder.start();

			process.waitFor();

			int exitCode = process.exitValue();
			if (exitCode == 0) {
				logger.info("process {} killed with success", processName);
			} else {
				logger.info("Error to kill process {} exitCode {}", processName, exitCode);
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void startProcess(String processName) {

		ProcessBuilder processBuilder = new ProcessBuilder(processName);

		try {
			Process process = processBuilder.start();
			while (!process.isAlive()) {
				logger.info("process {} not yet alive", processName);
				Utils.pause(500);
			}
			logger.info("process {} alive with pid {}", processName, process.pid());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}