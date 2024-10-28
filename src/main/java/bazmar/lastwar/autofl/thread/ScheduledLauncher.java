package bazmar.lastwar.autofl.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.LoggerFactory;

import bazmar.lastwar.autofl.LastWarMain;
import bazmar.lastwar.autofl.utils.ProcessManager;
import ch.qos.logback.classic.Logger;

public class ScheduledLauncher {

	private static Logger logger = (Logger) LoggerFactory.getLogger("ScheduledLauncher");

	private ScheduledExecutorService executor;

	public void autoRestartBot() {
		executor = Executors.newSingleThreadScheduledExecutor();
		Runnable task = () -> {
			logger.info("autoRestartBot");
			if (LastWarMain.PAUSE) {
				logger.info("autoRestartBot bypass (PAUSE)");
				return;
			}

			if (!LastWarMain.checkFlContext()) {

				if (LastWarMain.checkFlContextNeedRestart()) {
					logger.info("Restart process FL");
					ProcessManager.startBotFL();
					return;
				}
				LastWarMain.repositionningFlIfNeeded();
			}
		};

		long initialDelay = 0;
		long period = 30;
		TimeUnit timeUnit = TimeUnit.SECONDS;

		executor.scheduleAtFixedRate(task, initialDelay, period, timeUnit);
	}
}