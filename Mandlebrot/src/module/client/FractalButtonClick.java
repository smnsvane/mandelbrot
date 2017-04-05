package module.client;

import java.util.logging.Logger;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;

import module.shared.ColorGrid;
import module.shared.FractalProperties;

public class FractalButtonClick implements ClickHandler {

	private Logger logger = Logger.getLogger(getClass().getName());

	private CanvasSetup setup;
	private FractalProperties prop;

	public FractalButtonClick(CanvasSetup setup, FractalProperties prop) {
		this.setup = setup;
		this.prop = prop;
	}

	@Override
	public void onClick(ClickEvent event) {

		if (setup.serverSideCalc) {
			long start = System.currentTimeMillis();
			setup.colorGridService.calculateColorGrid(
					CanvasSetup.CANVAS_WIDTH_IN_PX, CanvasSetup.CANVAS_HEIGHT_IN_PX, prop,
					new AsyncCallback<ColorGrid>() {
				@Override
				public void onSuccess(ColorGrid result) {
					long end = System.currentTimeMillis();
					logger.info("benchmark on server fractal calc time (millisec): "+(end-start));
					setup.drawFractal(result);
				}
				@Override
				public void onFailure(Throwable caught) { throw new RuntimeException(caught); }
			});
		} else {
			ColorGrid grid = setup.clientCalculateFractal(prop);
			setup.drawFractal(grid);
		}
	}
}
