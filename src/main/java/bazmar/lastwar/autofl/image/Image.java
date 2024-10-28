package bazmar.lastwar.autofl.image;

import java.io.InputStream;

public class Image extends javafx.scene.image.Image {

	private String name;

	public Image(InputStream is) {
		super(is);
	}

	public Image(InputStream is, String name) {
		super(is);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
