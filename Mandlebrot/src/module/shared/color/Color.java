/**
 * I made this class because CssColor is not easily stored in collections
 */

package module.shared.color;

import com.google.gwt.canvas.dom.client.CssColor;
import com.google.gwt.user.client.rpc.IsSerializable;

public class Color implements IsSerializable {

	public int r, g, b;
	public Color(int red, int green, int blue) { r = red; g = green; b = blue; }

	// to keep GWT-RPC happy
	@SuppressWarnings("unused")
	private Color() {}

	public CssColor toCssColor() {
		return CssColor.make("rgb("+r+", "+g+", "+b+")");
	}

	public final static Color black = new Color(0, 0, 0);
	public final static Color white = new Color(255, 255, 255);
	public final static Color red = new Color(255, 0, 0);
	public final static Color green = new Color(0, 255, 0);
	public final static Color blue = new Color(0, 0, 255);
	public final static Color yellow = new Color(255, 255, 0);
	public final static Color magenta = new Color(255, 0, 255);
	public final static Color cyan = new Color(0, 255, 255);

	/**
	 * ripped off from java.awt.Color
	 * see http://www.docjar.com/html/api/java/awt/Color.java.html
	 * returns a Color object instead of an integer
	 */
	public static Color HSBtoRGB(float hue, float saturation, float brightness) {
		int r = 0, g = 0, b = 0;
		if (saturation == 0) {
			r = g = b = (int) (brightness * 255.0f + 0.5f);
		} else {
			float h = (hue - (float)Math.floor(hue)) * 6.0f;
			float f = h - (float)java.lang.Math.floor(h);
			float p = brightness * (1.0f - saturation);
			float q = brightness * (1.0f - saturation * f);
			float t = brightness * (1.0f - (saturation * (1.0f - f)));
			switch ((int) h) {
			case 0:
				r = (int) (brightness * 255.0f + 0.5f);
				g = (int) (t * 255.0f + 0.5f);
				b = (int) (p * 255.0f + 0.5f);
				break;
			case 1:
				r = (int) (q * 255.0f + 0.5f);
				g = (int) (brightness * 255.0f + 0.5f);
				b = (int) (p * 255.0f + 0.5f);
				break;
			case 2:
				r = (int) (p * 255.0f + 0.5f);
				g = (int) (brightness * 255.0f + 0.5f);
				b = (int) (t * 255.0f + 0.5f);
				break;
			case 3:
				r = (int) (p * 255.0f + 0.5f);
				g = (int) (q * 255.0f + 0.5f);
				b = (int) (brightness * 255.0f + 0.5f);
				break;
			case 4:
				r = (int) (t * 255.0f + 0.5f);
				g = (int) (p * 255.0f + 0.5f);
				b = (int) (brightness * 255.0f + 0.5f);
				break;
			case 5:
				r = (int) (brightness * 255.0f + 0.5f);
				g = (int) (p * 255.0f + 0.5f);
				b = (int) (q * 255.0f + 0.5f);
				break;
			}
		}
		return new Color(r, g, b);
//		return 0xff000000 | (r << 16) | (g << 8) | (b << 0);
	}
}
