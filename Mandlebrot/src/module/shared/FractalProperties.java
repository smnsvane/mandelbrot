package module.shared;

import module.shared.color.AxialColorGradient;
import module.shared.color.UltraFractal16;

public class FractalProperties {

	public float coordStartX; // canvas x = 0 will correspond to this coordinate x, default -2.2f
	public float coordStartY; // canvas y = 0 will correspond to this coordinate y, default -1.3f
	public float epsilon; // The step size across the X and Y axis (zoom level), default 0.0035f
	public int maxIterations; // increasing this will give you a more detailed fractal, default 256
	public AxialColorGradient gradient; // the color scheme used, default UltraColor
	public Complex z0; // the initial z value used, default (0, 0i)
	public int k; // for classical mandelbrot set use '2', default 2
	
	public FractalProperties() {
		coordStartX = -2.2f;
		coordStartY = -1.3f;
		epsilon = 0.0035f;
		maxIterations = 256;
		gradient = new UltraFractal16(); // the color scheme used, default UltraColor
		z0 = new Complex(0, 0); // the initial z value used, default (0, 0i)
		k = 2; // for classical mandelbrot set use '2', default 2
	}
}
