package bazmar.lastwar.autofl.image;

import javafx.scene.paint.Color;

public class ColorSimilar {

	public static boolean areColorsSimilar(Color color1, Color color2, int threshold) {
		double diffRed = Math.abs(color1.getRed() - color2.getRed()) * 255;
		double diffGreen = Math.abs(color1.getGreen() - color2.getGreen()) * 255;
		double diffBlue = Math.abs(color1.getBlue() - color2.getBlue()) * 255;

		double totalDiff = diffRed + diffGreen + diffBlue;
		return totalDiff <= threshold;
	}
}
