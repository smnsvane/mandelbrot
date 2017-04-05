/**
 * see http://mathworld.wolfram.com/MandelbrotSet.html
 */
package module.shared;

import java.util.logging.Logger;

import module.shared.color.Color;

public class Engine {

	private Logger logger = Logger.getLogger(getClass().getName());

	public ColorGrid calculateFractal(int width, int height, FractalProperties prop) {

		logger.info("calculating fractal...");

		ColorGrid grid = new ColorGrid(width, height);

		int x = 0, y = 0;
		for (float fractalX = prop.fractalX0; x < width; fractalX += prop.epsilon, x++) {
			for (float fractalY = prop.fractalY0; y < height; fractalY += prop.epsilon, y++) {

				int iterations = 0;
				Complex c = new Complex(fractalX, fractalY);
				Complex z = prop.z0.copy();
				while (z.absolute() < 2 && iterations < prop.maxIterations) {

					// classical mandelbrot formula: z(n+1) = z(n)^2 + c
					// generalized formula: z(n+1) = z(n)^k + c
					// where c is the coordinate of the complex plane (in this case the canvas)
					// and z(0) is (in classical mandelbrot) the complex number (0, 0i)
					Complex exponentiation = Complex.exponentiation(z, prop.k);
					z = Complex.addition(exponentiation, c);
					iterations++;
				}

				Color color = prop.gradient.getColor(iterations, prop.maxIterations);
				grid.grid[x][y] = color;

				if (y == 0 && x % 100 == 0)
					logger.info("calculation "+(100.0 * x / width)+"% done");

			}
			y = 0;
		}

		return grid;
	}
}
