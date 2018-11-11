package juego;

import java.io.IOException;
import java.lang.Thread.UncaughtExceptionHandler;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Captura errores en tiempo de ejecución y muestra una ventana con el detalle.
 */
public class MostrarError implements UncaughtExceptionHandler {
	
	@Override
	public void uncaughtException(Thread hilo, Throwable error) {

		Stage escenario = new Stage();
		
		String mensaje = new String(error.getMessage());

		Parent pantallaError = null;
		try {
			pantallaError = FXMLLoader.load(getClass().getResource("./../views/error.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Scene escena = new Scene(pantallaError, 450,130);
		Button btnCerrar = (Button) pantallaError.lookup("#btnCerrar");
		btnCerrar.addEventHandler(MouseEvent.MOUSE_CLICKED, e->{
			escenario.close();
		});
		Label errorText = (Label) pantallaError.lookup("#labelError");

		errorText.setText(mensaje);

		escenario.initStyle(StageStyle.UNDECORATED);
		escenario.getIcons().add(new Image("./img/icono.png"));
		WindowStyle.allowDrag(pantallaError, escenario);
		WindowStyle.stageDimension(escenario.getWidth(), escenario.getHeight());

		escenario.setScene(escena);
		escenario.setResizable(false);
		escenario.setTitle("¡Ups!");
		escenario.show();
	}

}
