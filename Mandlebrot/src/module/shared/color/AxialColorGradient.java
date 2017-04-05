package module.shared.color;

import com.google.gwt.user.client.rpc.IsSerializable;

public interface AxialColorGradient extends IsSerializable {

	/**
	 * Call to pick a color from the axial gradient (linear gradient)
	 * @param colorIndex the color index (in fractal context the number of iterations)
	 * @param length if the gradient have variable length, it will be controlled with this parameter<br/>
	 * (in fractal context maximum iterations)
	 * @return Color
	 */
	Color getColor(int colorIndex, int length);

}