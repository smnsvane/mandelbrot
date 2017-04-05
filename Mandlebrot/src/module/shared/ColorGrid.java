package module.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ColorGrid implements IsSerializable {

	public Color[][] grid;
	public ColorGrid(int width, int height) {
		grid = new Color[width][height];
	}
}
