package module.client;

import java.util.logging.Logger;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.ImageData;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import module.shared.ColorGrid;
import module.shared.Engine;
import module.shared.FractalProperties;
import module.shared.color.Color;

public class CanvasSetup implements EntryPoint {

	private Logger logger = Logger.getLogger(getClass().getName());

	public final static int CANVAS_WIDTH_IN_PX = 1000; // width of the HTML5 Canvas
	public final static int CANVAS_HEIGHT_IN_PX = 700; // height of the HTML5 Canvas

	private Canvas canvas; // the canvas

	/**
	 * whenever the fractal calculation is done on client or server side
	 */
	public final boolean serverSideCalc = true;
	public final ColorGridServiceAsync colorGridService = GWT.create(ColorGridService.class);

	// performance optimization (see drawPixel method)
	public final boolean fastPixelDraw = true;
	private ImageData canvasImageData;

	public void onModuleLoad() {

		// GWT boilerplate and HTML5 Canvas
		RootPanel root = RootPanel.get("main");
		// Verify browser have HTML5 Canvas support
		canvas = Canvas.createIfSupported();
		if (canvas == null) {
			root.add(new Label("Your browser do not support HTML5 Canvas"));
			return;
		}
		canvas.setWidth(CANVAS_WIDTH_IN_PX+"px");
		canvas.setHeight(CANVAS_HEIGHT_IN_PX+"px");
		canvas.setCoordinateSpaceWidth(CANVAS_WIDTH_IN_PX);
		canvas.setCoordinateSpaceHeight(CANVAS_HEIGHT_IN_PX);

		// layout setup
		VerticalPanel vPanel = new VerticalPanel();
		FlowPanel div = new FlowPanel();
		Button classicMandelbrotButton = new Button("Classic Mandelbrot",
				new FractalButtonClick(this, FractalProperties.classicMandelbrot()));
		Button seahorseVallyButton = new Button("Seahorse Vally",
				new FractalButtonClick(this, FractalProperties.seahorseVally()));

		// layout assembly
		div.add(classicMandelbrotButton);
		div.add(seahorseVallyButton);
		vPanel.add(div);
		vPanel.add(canvas);
		root.add(vPanel);
	}

	public ColorGrid clientCalculateFractal(FractalProperties prop) {

		long start = System.currentTimeMillis();
		ColorGrid colorGrid = new Engine().calculateFractal(CANVAS_WIDTH_IN_PX, CANVAS_HEIGHT_IN_PX, prop);
		long end = System.currentTimeMillis();
		logger.info("benchmark on client fractal calc time (millisec): "+(end-start));
		return colorGrid;
	}

	public void drawFractal(ColorGrid colorGrid) {

		long start = System.currentTimeMillis();
		Context2d ctx = canvas.getContext2d();
		canvasImageData = ctx.getImageData(0, 0, CanvasSetup.CANVAS_WIDTH_IN_PX, CanvasSetup.CANVAS_HEIGHT_IN_PX);

		for (int x = 0; x < CANVAS_WIDTH_IN_PX; x++)
			for (int y = 0; y < CANVAS_HEIGHT_IN_PX; y++)
				drawPixel(x, y, ctx, colorGrid.grid[x][y]);

		// performance optimization (see drawPixel method)
		if (fastPixelDraw)
			ctx.putImageData(canvasImageData, 0, 0);

		long end = System.currentTimeMillis();
		logger.info("benchmark on draw time (millisec): "+(end-start));
	}

	/**
	 * Used to draw a single pixel to the canvas, GWT have no good out-of-the-box solution for this
	 * @param x canvas x coordinate of the pixel
	 * @param y canvas y coordinate of the pixel
	 * @param ctx Context2d object used as drawing surface for the canvas
	 * @return Color the color selected for the given pixel
	 */
	private void drawPixel(int x, int y, Context2d ctx, Color color) {

		if (!fastPixelDraw) {

			// the simple pixel draw solution
			ctx.setFillStyle(color.toCssColor());
			ctx.fillRect(x, y, 1, 1);

		} else {

			// more complex and faster pixel draw solution,
			// cuts the drawing time by about a factor five compared to the simple solution
			// this might be a slower solution than the simple one, if only a few pixels are drawn, remember to test!
			int index = (x + y * CanvasSetup.CANVAS_WIDTH_IN_PX) * 4;
			canvasImageData.getData().set(index + 0, color.r); // red
			canvasImageData.getData().set(index + 1, color.g); // green
			canvasImageData.getData().set(index + 2, color.b); // blue
			canvasImageData.getData().set(index + 3, 255); // alpha channel (opacity) set to 100%
		}
	}
}
