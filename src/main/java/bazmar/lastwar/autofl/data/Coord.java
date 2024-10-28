package bazmar.lastwar.autofl.data;

public class Coord {

	public int x;
	public int y;
	int height;
	int width;
	String name;

	public Coord(int x, int y, int height, int width, String name) {
		super();
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
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

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getLeftBottomX() {
		return x;
	}

	public int getRightBottomX() {
		return x + width;
	}

	public int getRightTopX() {
		return x + width;
	}

	public int getLeftBottomY() {
		return y + height;
	}

	public int getRightBottomY() {
		return y + height;
	}

	public int getRightTopY() {
		return y;
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

	@Override
	public String toString() {
		return "Coord [x=" + x + ", y=" + y + ", height=" + height + ", width=" + width + ", name=" + name + "]";
	}

}
