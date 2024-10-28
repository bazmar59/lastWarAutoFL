package bazmar.lastwar.autofl.io;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.slf4j.LoggerFactory;

import bazmar.lastwar.autofl.LastWarMain;
import ch.qos.logback.classic.Logger;

public class KeyScanner implements NativeKeyListener {
	private volatile boolean running = true;

	private static Logger logger = (Logger) LoggerFactory.getLogger("KeyScanner");

	public KeyScanner() {
		try {
			GlobalScreen.registerNativeHook();
		} catch (NativeHookException e) {
			e.printStackTrace();
		}

		GlobalScreen.addNativeKeyListener(this);
	}

	@Override
	public void nativeKeyPressed(NativeKeyEvent e) {
		String keyText = NativeKeyEvent.getKeyText(e.getKeyCode());
		logger.debug("Key Pressed: " + keyText);

		if (keyText.equalsIgnoreCase("Echap") || keyText.equalsIgnoreCase("Escape")) {
			logger.info("Exit");
			System.exit(0);
		}
		if (keyText.equalsIgnoreCase("pause")) {
			tooglePause();
			logger.info("Pause or Restart PAUSE=%s".formatted(LastWarMain.PAUSE));
		}
	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent e) {
		// Can be used to handle key release events
	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent e) {
		// Can be used to handle key typed events
	}

	public boolean isRunning() {
		return running;
	}

	private void tooglePause() {
		LastWarMain.PAUSE = !LastWarMain.PAUSE;
		Frame.updateStatusMessage(LastWarMain.PAUSE);
	}
}