package module.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import module.shared.ColorGrid;
import module.shared.FractalProperties;

public interface ColorGridServiceAsync {

	void calculateColorGrid(int width, int height, FractalProperties prop, AsyncCallback<ColorGrid> callback);

}
