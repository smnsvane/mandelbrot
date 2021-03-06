package module.shared.color;

public class LinearGreenGradient implements AxialColorGradient {

	@Override
	public Color getColor(int colorIndex, int length) {
		colorIndex = colorIndex % 256;
		return new Color(0, colorIndex, 0);
	}
}
