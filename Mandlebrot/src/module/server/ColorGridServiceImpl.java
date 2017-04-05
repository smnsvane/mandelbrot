package module.server;

import module.client.ColorGridService;
import module.shared.ColorGrid;
import module.shared.Engine;
import module.shared.FractalProperties;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class ColorGridServiceImpl extends RemoteServiceServlet implements ColorGridService {

	@Override
	public ColorGrid calculateColorGrid(int width, int height, FractalProperties prop) throws IllegalArgumentException {
		ColorGrid grid = new Engine().calculateFractal(width, height, prop);
		return grid;
	}
}
