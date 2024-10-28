package bazmar.lastwar.autofl.io;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;

public class Keyboard {

	private static Logger logger = (Logger) LoggerFactory.getLogger("Keyboard");

	public static void writeText(String input) {
		if (input != null) {
			input = input.concat("\n");

			logger.info("writeText : %s".formatted(input));
			try {
				Robot robot = new Robot();

				for (char c : input.toCharArray()) {
					boolean isUpperCase = Character.isUpperCase(c);
					int keyCode = getKeyCode(c);

					if (keyCode != -1) {
						if (isUpperCase) {
							robot.keyPress(KeyEvent.VK_SHIFT);
						}

						robot.keyPress(keyCode);
						robot.keyRelease(keyCode);

						if (isUpperCase) {
							robot.keyRelease(KeyEvent.VK_SHIFT);
						}

						robot.delay(100);
					} else {
						handleSpecialCharacter(robot, c);
					}
				}
			} catch (AWTException e) {
				e.printStackTrace();
			}
		} else {
			logger.info("writeText : input is null");
		}
	}

	private static int getKeyCode(char c) {
		switch (Character.toLowerCase(c)) {
		case 'a':
			return KeyEvent.VK_A;
		case 'b':
			return KeyEvent.VK_B;
		case 'c':
			return KeyEvent.VK_C;
		case 'd':
			return KeyEvent.VK_D;
		case 'e':
			return KeyEvent.VK_E;
		case 'f':
			return KeyEvent.VK_F;
		case 'g':
			return KeyEvent.VK_G;
		case 'h':
			return KeyEvent.VK_H;
		case 'i':
			return KeyEvent.VK_I;
		case 'j':
			return KeyEvent.VK_J;
		case 'k':
			return KeyEvent.VK_K;
		case 'l':
			return KeyEvent.VK_L;
		case 'm':
			return KeyEvent.VK_M;
		case 'n':
			return KeyEvent.VK_N;
		case 'o':
			return KeyEvent.VK_O;
		case 'p':
			return KeyEvent.VK_P;
		case 'q':
			return KeyEvent.VK_Q;
		case 'r':
			return KeyEvent.VK_R;
		case 's':
			return KeyEvent.VK_S;
		case 't':
			return KeyEvent.VK_T;
		case 'u':
			return KeyEvent.VK_U;
		case 'v':
			return KeyEvent.VK_V;
		case 'w':
			return KeyEvent.VK_W;
		case 'x':
			return KeyEvent.VK_X;
		case 'y':
			return KeyEvent.VK_Y;
		case 'z':
			return KeyEvent.VK_Z;
		case '0':
			return KeyEvent.VK_0;
		case '1':
			return KeyEvent.VK_1;
		case '2':
			return KeyEvent.VK_2;
		case '3':
			return KeyEvent.VK_3;
		case '4':
			return KeyEvent.VK_4;
		case '5':
			return KeyEvent.VK_5;
		case '6':
			return KeyEvent.VK_6;
		case '7':
			return KeyEvent.VK_7;
		case '8':
			return KeyEvent.VK_8;
		case '9':
			return KeyEvent.VK_9;
		case ' ':
			return KeyEvent.VK_SPACE;
		default:
			logger.error("Unsupported char : " + c);
			return -1;
		}
	}

	private static void handleSpecialCharacter(Robot robot, char c) {
		try {
			switch (c) {
			case '!':
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_1);
				robot.keyRelease(KeyEvent.VK_1);
				robot.keyRelease(KeyEvent.VK_SHIFT);
				break;
			case '@':
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_2);
				robot.keyRelease(KeyEvent.VK_2);
				robot.keyRelease(KeyEvent.VK_SHIFT);
				break;
			case '#':
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_3);
				robot.keyRelease(KeyEvent.VK_3);
				robot.keyRelease(KeyEvent.VK_SHIFT);
				break;
			case 'é':
				robot.keyPress(KeyEvent.VK_ALT_GRAPH);
				robot.keyPress(KeyEvent.VK_E);
				robot.keyRelease(KeyEvent.VK_E);
				robot.keyRelease(KeyEvent.VK_ALT_GRAPH);
				break;
			case 'è':
				robot.keyPress(KeyEvent.VK_BACK_QUOTE);
				robot.keyPress(KeyEvent.VK_E);
				robot.keyRelease(KeyEvent.VK_E);
				robot.keyRelease(KeyEvent.VK_BACK_QUOTE);
				break;
			case 'à':
				robot.keyPress(KeyEvent.VK_BACK_QUOTE);
				robot.keyPress(KeyEvent.VK_A);
				robot.keyRelease(KeyEvent.VK_A);
				robot.keyRelease(KeyEvent.VK_BACK_QUOTE);
				break;
			case 'ç':
				robot.keyPress(KeyEvent.VK_ALT_GRAPH);
				robot.keyPress(KeyEvent.VK_C);
				robot.keyRelease(KeyEvent.VK_C);
				robot.keyRelease(KeyEvent.VK_ALT_GRAPH);
				break;
			case 'ù':
				robot.keyPress(KeyEvent.VK_ALT_GRAPH);
				robot.keyPress(KeyEvent.VK_U);
				robot.keyRelease(KeyEvent.VK_U);
				robot.keyRelease(KeyEvent.VK_ALT_GRAPH);
				break;
			case '?':
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_SLASH);
				robot.keyRelease(KeyEvent.VK_SLASH);
				robot.keyRelease(KeyEvent.VK_SHIFT);
				break;
			case '\'':
				robot.keyPress(KeyEvent.VK_QUOTE);
				robot.keyRelease(KeyEvent.VK_QUOTE);
				break;
			case '-':
				robot.keyPress(KeyEvent.VK_MINUS);
				robot.keyRelease(KeyEvent.VK_MINUS);
				break;
			case ':':
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_SEMICOLON);
				robot.keyRelease(KeyEvent.VK_SEMICOLON);
				robot.keyRelease(KeyEvent.VK_SHIFT);
				break;
			case ')':
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_0);
				robot.keyRelease(KeyEvent.VK_0);
				robot.keyRelease(KeyEvent.VK_SHIFT);
				break;
			case '\n':
			case '\r':
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
				break;
			default:
				logger.error("Unsupported char : " + c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
