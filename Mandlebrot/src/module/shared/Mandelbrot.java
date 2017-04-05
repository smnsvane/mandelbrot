/**
 * see http://mathworld.wolfram.com/MandelbrotSet.html
 */
package module.shared;

import java.util.logging.Logger;

import module.shared.color.AxialColorGradient;
import module.shared.color.Color;
import module.shared.color.GradientOne;

public class Mandelbrot {

	private Logger logger = Logger.getLogger(getClass().getName());

	private float coordStartX = -1.1f; // canvas x = 0 will correspond to this coordinate x, default -2.2f
	private float coordStartY = -0.4f; // canvas y = 0 will correspond to this coordinate y, default -1.3f
	private float epsilon = 0.0005f; // The step size across the X and Y axis (zoom level), default 0.0035f
	private int maxIterations = 16; // increasing this will give you a more detailed fractal, default 256
	private AxialColorGradient colors = new GradientOne(); // the color scheme used, default UltraColor
	private Complex z0 = new Complex(0, 0); // the initial z value used, default (0, 0i)
	private int k = 2; // for classical mandelbrot set use '2', default 2

	public ColorGrid calculateFractal(int width, int height) {

		logger.info("calculating fractal...");

		ColorGrid grid = new ColorGrid(width, height);

		int x = 0, y = 0;
		for (float coordX = coordStartX; x < width; coordX += epsilon, x++) {
			for (float coordY = coordStartY; y < height; coordY += epsilon, y++) {

				int iterations = 0;
				Complex c = new Complex(coordX, coordY);
				Complex z = z0.copy();
				while (z.absolute() < 2 && iterations < maxIterations) {

					// classical mandelbrot formula: z(n+1) = z(n)^2 + c
					// generalized formula: z(n+1) = z(n)^k + c
					// where c is the coordinate of the complex plane (in this case the canvas)
					// and z(0) is (in classical mandelbrot) the complex number (0, 0i)
					Complex exponentiation = Complex.exponentiation(z, k);
					z = Complex.addition(exponentiation, c);
					iterations++;
				}

				Color color = calculatePixelColor(iterations);
				grid.grid[x][y] = color;

				if (y == 0 && x % 100 == 0)
					logger.info("calculation "+(100.0 * x / width)+"% done");

			}
			y = 0;
		}

		return grid;
	}

	private Color calculatePixelColor(int iteration) {

		if (iteration < maxIterations)
			return colors.getColor(iteration, maxIterations);
		return Color.black;
	}
}
