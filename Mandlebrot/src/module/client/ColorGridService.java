package module.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import module.shared.ColorGrid;
import module.shared.FractalProperties;

@RemoteServiceRelativePath("colorgrid")
public interface ColorGridService extends RemoteService {

	ColorGrid calculateColorGrid(int width, int height, FractalProperties prop) throws IllegalArgumentException;
}
