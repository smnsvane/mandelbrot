/**
 * ripped off asker's algorithm
 * from http://stackoverflow.com/questions/16500656/which-color-gradient-is-used-to-color-mandelbrot-in-wikipedia
 */
package module.shared.color;

public class GreenGradient implements AxialColorGradient {

	@Override
	public Color getColor(int colorIndex, int length) {
		int quotient = 255 * colorIndex / length;
		if (quotient >= 128) // half of 256
			return new Color(quotient, 255, quotient);
		return new Color(0, quotient, 0);
	}
}