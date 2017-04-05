package module.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

import module.shared.color.AxialColorGradient;
import module.shared.color.GradientOne;
import module.shared.color.UltraFractal16;

public class FractalProperties implements IsSerializable {

	/**
	 * canvas x = 0 will correspond to this coordinate x, default -2.2f
	 */
	public float fractalX0;
	/**
	 * canvas y = 0 will correspond to this coordinate y, default -1.3f
	 */
	public float fractalY0;
	/**
	 * The step size across the X and Y axis (zoom level), default 0.0035f
	 */
	public float epsilon;
	/**
	 * increasing this will give you a more detailed fractal, default 256
	 */
	public int maxIterations;
	/**
	 * the color scheme used, default UltraColor
	 */
	public AxialColorGradient gradient;
	/**
	 * the initial z value used, default (0, 0i)
	 */
	public Complex z0;
	/**
	 * exponent in formula, for classical mandelbrot set use '2', default 2
	 */
	public int k;

	public FractalProperties() {}

	public FractalProperties(float coordStartX, float coordStartY,
			float epsilon, int maxIterations,
			AxialColorGradient gradient, Complex z0, int k) {
		this.fractalX0 = coordStartX;
		this.fractalY0 = coordStartY;
		this.epsilon = epsilon;
		this.maxIterations = maxIterations;
		this.gradient = gradient;
		this.z0 = z0;
		this.k = k;
	}

	public static FractalProperties classicMandelbrot() {
		FractalProperties prop = new FractalProperties();
		prop.fractalX0 = -2.2f;
		prop.fractalY0 = -1.3f;
		prop.epsilon = 0.0035f;
		prop.maxIterations = 2048;
		prop.gradient = new UltraFractal16();
		prop.z0 = new Complex(0, 0);
		prop.k = 2;
		return prop;
	}

	public static FractalProperties seahorseVally() {
		FractalProperties prop = new FractalProperties();
		prop.fractalX0 = -1.1f;
		prop.fractalY0 = -0.4f;
		prop.epsilon = 0.0005f;
		prop.maxIterations = 2048;
		prop.gradient = new GradientOne();
		prop.z0 = new Complex(0, 0);
		prop.k = 2;
		return prop;
	}
}
