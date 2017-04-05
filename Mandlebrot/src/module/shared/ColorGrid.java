package module.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

import module.shared.color.Color;

public class ColorGrid implements IsSerializable {

	public Color[][] grid;
	public ColorGrid(int width, int height) {
		grid = new Color[width][height];
	}

	// to keep GWT-RPC happy
	@SuppressWarnings("unused")
	private ColorGrid() {}
}
