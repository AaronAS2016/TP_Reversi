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

	private Parent pantallaError;
	private Stage escenario;
	private Scene escena;
	
	@Override
	public void uncaughtException(Thread hilo, Throwable error) {

		escenario = new Stage();

		try {
			configurarVista();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		configuramosEscena(error);

		configuramosBotones();

		configurarVentana();
	}

	private void configurarVista() throws IOException {
		pantallaError = FXMLLoader.load(getClass().getResource(
				"./../views/error.fxml"));

	}

	private void configuramosBotones(){

		Button btnCerrar = (Button) pantallaError.lookup("#btnCerrar");
		btnCerrar.addEventHandler(MouseEvent.MOUSE_CLICKED, e->{
			escenario.close();
		});


	}
	private void configuramosEscena(  Throwable error){
		String mensaje = new String(error.getMessage());

		escena = new Scene(pantallaError, 450,130);

		Label errorText = (Label) pantallaError.lookup("#labelError");

		errorText.setText(mensaje);
	}
	private void configurarVentana(){
		EstilizarVentana.allowDrag(pantallaError, escenario);
		EstilizarVentana.stageDimension(escenario.getWidth(), escenario.getHeight());

		escenario.initStyle(StageStyle.UNDECORATED);
		escenario.getIcons().add(new Image("./img/icono.png"));
		escenario.setScene(escena);
		escenario.setResizable(false);
		escenario.setTitle("¡Ups!");
		escenario.show();

	}

}
