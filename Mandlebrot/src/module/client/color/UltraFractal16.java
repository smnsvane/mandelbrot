/**
 * ripped off from responder in
 * http://stackoverflow.com/questions/16500656/which-color-gradient-is-used-to-color-mandelbrot-in-wikipedia
 */
package module.client.color;

import java.util.ArrayList;

import module.shared.Color;

public class UltraFractal16 implements AxialColorGradient {

	private ArrayList<Color> colors = new ArrayList<>();
	public UltraFractal16() {
		colors.add(new Color( 66,  30,  15));
		colors.add(new Color( 25,   7,  26));
		colors.add(new Color(  9,   1,  47));
		colors.add(new Color(  4,   4,  73));
		colors.add(new Color(  0,   7, 100));
		colors.add(new Color( 12,  44, 138));
		colors.add(new Color( 24,  82, 177));
		colors.add(new Color( 57, 125, 209));
		colors.add(new Color(134, 181, 229));
		colors.add(new Color(211, 236, 248));
		colors.add(new Color(241, 233, 191));
		colors.add(new Color(248, 201,  95));
		colors.add(new Color(255, 170,   0));
		colors.add(new Color(204, 128,   0));
		colors.add(new Color(153,  87,   0));
		colors.add(new Color(106,  52,   3));
	}

	/* (non-Javadoc)
	 * @see module.client.color.FractalColor#getColor(int, int)
	 */
	@Override
	public Color getColor(int colorIndex, int length) {

		int i = colorIndex % colors.size();
		Color c = colors.get(i);
		return c;
	}
}
