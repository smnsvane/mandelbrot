package module.server;

import module.client.ColorGridService;
import module.shared.ColorGrid;
import module.shared.FractalProperties;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class ColorGridServiceImpl extends RemoteServiceServlet implements ColorGridService {

	@Override
	public ColorGrid calculateColorGrid(FractalProperties prop) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
}
