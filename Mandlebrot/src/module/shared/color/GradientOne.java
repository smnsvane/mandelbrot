package module.shared.color;

public class GradientOne implements AxialColorGradient {

	@Override
	public Color getColor(int colorIndex, int length) {

		if (colorIndex < 32) //1,5625
			return new Color(colorIndex * 2, 0, 0);    /* 0x0000 to 0x007E */
		if (colorIndex < 64) //3,125
			return new Color((((colorIndex - 64) * 128) / 126) + 128, 0, 0);    /* 0x0080 to 0x00C0 */
		if (colorIndex < 128) //6,25
			return new Color((((colorIndex - 128) * 62) / 127) + 193, 0, 0);    /* 0x00C1 to 0x00FF */
		if (colorIndex < 256) //12,5
			return new Color(255, (((colorIndex - 256) * 62) / 255) + 1, 0);    /* 0x01FF to 0x3FFF */
		if (colorIndex < 512) //25
			return new Color(255, (((colorIndex - 512) * 63) / 511) + 64, 0);   /* 0x40FF to 0x7FFF */
		if (colorIndex < 1024) //50
			return new Color(255, (((colorIndex - 1024) * 63) / 1023) + 128, 0);   /* 0x80FF to 0xBFFF */
		if (colorIndex < 2048) //100
			return new Color(255, (((colorIndex - 2048) * 63) / 2047) + 192, 0);   /* 0xC0FF to 0xFFFF */

		return Color.black;
	}
}
