package juego;

import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;

/* Clase con m�todos para poder personalizar la ventana*/

public class EstilizarVentana {
	private static final Rectangle2D SCREEN_BOUNDS = Screen.getPrimary()
			.getVisualBounds();
	private static double[] pref_WH, offset_XY;
	private static String styleSheet;

	private EstilizarVentana(String css) {
		styleSheet = getClass().getResource(css).toString();
	}

	protected static void allowDrag(Parent root, Stage stage) {
		root.setOnMousePressed((MouseEvent p) -> {
			offset_XY = new double[] { p.getSceneX(), p.getSceneY() };
		});

		root.setOnMouseDragged((MouseEvent d) -> {
			if (d.getScreenY() < (SCREEN_BOUNDS.getMaxY() - 20))
				stage.setY(d.getScreenY() - offset_XY[1]);
			stage.setX(d.getScreenX() - offset_XY[0]);
		});

		root.setOnMouseReleased((MouseEvent r) -> {
			if (stage.getY() < 0.0)
				stage.setY(0.0);
		});
	}

	protected static void stageDimension(Double width, Double height) {
		pref_WH = new double[] { width, height };
	}

}