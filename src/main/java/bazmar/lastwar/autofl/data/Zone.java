package bazmar.lastwar.autofl.data;

public class Zone {
	private int x, y, width, height;
	private boolean inGame;
	private String name;
	private int screenIndex;

	public Zone(int x, int y, int width, int height, boolean inGame, Context context, String name) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.inGame = inGame;
		this.screenIndex = context.screenIndex();
		this.name = name;
	}

	public Zone(int x, int y, int width, int height, int screenIndex, String name) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.inGame = false;
		this.screenIndex = screenIndex;
		this.name = name;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
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

	public boolean isInGame() {
		return inGame;
	}

	public void setInGame(boolean inGame) {
		this.inGame = inGame;
	}

	public int getScreenIndex() {
		return screenIndex;
	}

	public int getCenterX() {
		return x + (width / 2);
	}

	public int getCenterY() {
		return y + (height / 2);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
