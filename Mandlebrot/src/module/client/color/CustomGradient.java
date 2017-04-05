package module.client.color;

import java.util.ArrayList;

import module.shared.Color;

public abstract class CustomGradient implements AxialColorGradient {

	class GradientPoint {
		public final float point;
		public final Color color;
		public GradientPoint(float point, Color color) {
			this.point = point;
			this.color = color;
		}
	}

	private ArrayList<GradientPoint> gradientPoints;
	public CustomGradient(ArrayList<GradientPoint> gradientPoints) { this.gradientPoints = gradientPoints; }
	
	@Override
	public Color getColor(int colorIndex, int length) {

		float quotient = (float) colorIndex / (float) length;
		GradientPoint before = gradientPoints.get(0);
		GradientPoint after = null;
		for (int i = 1; i < gradientPoints.size(); i++) {
			GradientPoint gPoint = gradientPoints.get(i);
			if (quotient < gPoint.point) {
				after = gPoint;
				break;
			} else
				before = gPoint;
		}

		if (after == null)
			throw new RuntimeException(getClass().getName()+" is missing a final gradient point");

		// linear color calculation
		quotient -= before.point;
		quotient /= after.point - before.point;
		
		return Color.yellow;
	}
}
