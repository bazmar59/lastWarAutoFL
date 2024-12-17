package bazmar.lastwar.autofl.io;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Robot;
import java.awt.event.InputEvent;

import org.slf4j.LoggerFactory;

import bazmar.lastwar.autofl.data.Constants;
import bazmar.lastwar.autofl.data.Context;
import bazmar.lastwar.autofl.data.Coord;
import bazmar.lastwar.autofl.data.Zone;
import bazmar.lastwar.autofl.utils.Utils;
import ch.qos.logback.classic.Logger;

public class Mouse {

	private static Logger logger = (Logger) LoggerFactory.getLogger("Mouse");

	private int origX = 0;
	private int origY = 0;
	private int width = 1920;
	private int height = 1080;
	private int screenIndex = 0;
	private Context context;
	private Screen screen;

	static Mouse instance = null;

	private Mouse(Context context) {
		origX = context.origX();
		origY = context.origY();
		width = context.width();
		height = context.height();
		screenIndex = context.screenIndex();
		this.context = context;
		screen = Screen.createInstance(context);
	}

	public static Mouse getInstance(Context context) {
		if (instance == null) {
			instance = new Mouse(context);
		}
		return instance;
	}

	public static Mouse createInstance(Context context) {
		instance = new Mouse(context);
		return instance;
	}

	public void click(int x, int y, String imgName) {
		click(x, y, origX, origY, imgName);
	}

	public void clickWithoutOrigin(int x, int y, String imgName) {
		click(x, y, 0, 0, imgName);
	}

	public void click(int x, int y, int origX, int origY, String imgName) {
		click(x, y, origX, origY, screenIndex, imgName);
	}

	public void click(int x, int y, int origX, int origY, int screenIndex, String imgName) {
		try {

			// Get the default screen device
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			GraphicsDevice[] screens = ge.getScreenDevices();

			java.awt.Rectangle screenBounds = screens[screenIndex].getDefaultConfiguration().getBounds();

			Robot robot = new Robot();

			int realX = screenBounds.x + x + origX;
			int realY = screenBounds.y + y + origY;
			robot.mouseMove(realX, realY);

			logger.info("click x={} y={} imgName={}", x, y, imgName);
			logger.debug("click origX={} origY={} screenBounds.x={} screenBounds.y={} realX={} realY={}", origX, origY,
					screenBounds.x, screenBounds.y, realX, realY);
			screen.screen(Constants.MOUSE_SCREEN_PATH + "click-%s%s".formatted(imgName, Utils.readableDate()) + ".png",
					origX + x - 30, origY + y - 30, 60, 60);

			// Simulate a mouse left click
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK); // Press the left mouse button
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); // Release the left mouse button

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public synchronized void drag(int x, int y) {
		drag(x, y, false);
	}

	public synchronized void drag(int x, int y, boolean fromCenter) {
		if (fromCenter) {
			dragFrom(x, y, width / 2, height / 2, true);
		} else {
			dragFrom(x, y, 0, 0, false);
		}
	}

	public synchronized void dragFrom(int x, int y, int fromX, int fromY, boolean useFrom) {
		dragFrom(x, y, fromX, fromY, useFrom, screenIndex);
	}

	public synchronized void dragFrom(int x, int y, int fromX, int fromY, boolean useFrom, int screenIndex) {
		try {
			int startx = 0;
			int starty = 0;
			if (useFrom) {
				startx = fromX;
				starty = fromY;
			} else {

				if (x + 20 > width) {
					x = width;
				}
				if (y + 120 > height) {
					y = height;
				}

				if (x == 0) {
					startx = width / 2;
				}
				if (x > 0) {
					startx = width - 20;
				}
				if (x < 0) {
					startx = 20;
				}

				if (y == 0) {
					starty = height / 2;
				}
				if (y > 0) {
					starty = height - 120;
				}
				if (y < 0) {
					starty = 120;
				}
			}

			// Get the default screen device
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			GraphicsDevice[] screens = ge.getScreenDevices();

			java.awt.Rectangle screenBounds = screens[screenIndex].getDefaultConfiguration().getBounds();

			// Create a Robot object
			Robot robot = new Robot();

			int x1 = screenBounds.x + startx + origX;
			int x2 = screenBounds.x + startx - x + origX;
			int y1 = screenBounds.y + starty - y + origY;
			int y2 = screenBounds.y + starty + origY;
			logger.debug("drag {} {} (x1={} x2={} y1={} y2={} screenBounds.x={} screenBounds.y={})", x, y, x1, x2, y1,
					y2, screenBounds.x, screenBounds.y);

			robot.mouseMove(x1, y1);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK); // Press the left mouse button
			int movex = x1;
			int movey = y1;
			int iter = 0;
			while (movex != x2 || movey != y2) {
				iter++;
				if (movex != x2) {
					if (movex > x2) {
						movex--;
					} else {
						movex++;
					}
				}
				if (movey != y2) {
					if (movey > y2) {
						movey--;
					} else {
						movey++;
					}
				}
				if (iter % 5 == 0) {
					Utils.pause(1);
				}
				robot.mouseMove(movex, movey);
			}
			Utils.pause(20);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); // Release the left mouse button
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public synchronized void drag(int fromX, int fromY, int toX, int toY, int screenIndex) {
		try {

			// Get the default screen device
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			GraphicsDevice[] screens = ge.getScreenDevices();

			java.awt.Rectangle screenBounds = screens[screenIndex].getDefaultConfiguration().getBounds();

			// Create a Robot object
			Robot robot = new Robot();
			robot.delay(100);

			int x1 = screenBounds.x + fromX;
			int x2 = screenBounds.x + toX;
			int y1 = screenBounds.y + fromY;
			int y2 = screenBounds.y + toY;
			logger.debug("drag {} {} (x1={} y1={} x2={} y2={} screenBounds.x={} screenBounds.y={})", x1, y1, x2, y2,
					screenBounds.x, screenBounds.y);
			screen.screen(Constants.MOUSE_SCREEN_PATH + "dragStart-" + Utils.readableDate() + ".png", x1 - 30, y1 - 30,
					60, 60);

			robot.mouseMove(x1, y1);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK); // Press the left mouse button
			int movex = x1;
			int movey = y1;
			int iter = 0;
			while (movex != x2 || movey != y2) {
				iter++;
				if (movex != x2) {
					if (movex > x2) {
						movex--;
					} else {
						movex++;
					}
				}
				if (movey != y2) {
					if (movey > y2) {
						movey--;
					} else {
						movey++;
					}
				}
				if (iter % 10 == 0) {
					Utils.pause(1);
				}
				robot.mouseMove(movex, movey);
			}

			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); // Release the left mouse button
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public synchronized void dragSameCoord() {
		try {

			int startx = width / 2;
			int starty = height / 2;

			// Get the default screen device
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			GraphicsDevice[] screens = ge.getScreenDevices();

			java.awt.Rectangle screenBounds = screens[screenIndex].getDefaultConfiguration().getBounds();

			// Create a Robot object
			Robot robot = new Robot();
			robot.delay(100);

			int x1 = screenBounds.x + startx + origX;
			int y1 = screenBounds.y + starty + origY;

			int x2 = screenBounds.x + startx + 200 + origX;

			logger.debug("dragSameCoord (x1={} x2={} y1={})", x1, x2, y1);

			robot.mouseMove(x1, y1);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK); // Press the left mouse button
			int movex = x1;
			int iter = 0;
			while (movex != x2) {
				iter++;
				if (movex != x2) {
					if (movex > x2) {
						movex--;
					} else {
						movex++;
					}
				}

				if (iter % 10 == 0) {
					Utils.pause(1);
				}
				robot.mouseMove(movex, y1);
			}

			iter = 0;
			while (movex != x1 || iter < 1000) {
				iter++;
				if (movex != x1) {
					if (movex > x1) {
						movex--;
					} else {
						movex++;
					}
				}

				if (iter % 10 == 0) {
					Utils.pause(1);
				}
				robot.mouseMove(movex, y1);
			}

			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); // Release the left mouse button
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void clickCenter(Coord coord) {
		clickCenter(coord, null);
	}

	public void clickCenter(Coord coord, String name) {
		if (coord != null) {
			click(coord.getCenterX(), coord.getCenterY(), name);
		} else {
			logger.error("Unable to click center with coord null with name %s".formatted(name));
		}
	}

	public void clickCenter(Zone zone) {
		if (zone != null) {
			click(zone.getCenterX(), zone.getCenterY(), zone.getName());
		} else {
			logger.error("Unable to click center with zone null");
		}
	}

	public void clickTopCenter(Zone zone) {
		if (zone != null) {
			click(zone.getCenterX(), zone.getY(), zone.getName());
		} else {
			logger.error("Unable to click TopCenter with zone null");
		}
	}

	public void clickCenterWithoutOrigin(Coord coord) {
		clickCenterWithoutOrigin(coord, null);
	}

	public void clickCenterWithoutOrigin(Coord coord, String name) {
		if (coord != null) {
			clickWithoutOrigin(coord.getCenterX(), coord.getCenterY(), name);
		} else {
			logger.error("Unable to click center without origin with coord null");
		}
	}

	@Override
	public String toString() {
		return "Mouse [origX=" + origX + ", origY=" + origY + ", width=" + width + ", height=" + height
				+ ", screenIndex=" + screenIndex + ", context=" + context + ", screen=" + screen + "]";
	}

}
