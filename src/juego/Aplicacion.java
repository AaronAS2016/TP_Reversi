package juego;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;

public class Aplicacion extends Application {

	/**
	 * Aplicación del juego Reversi.
	 * 
	 * Punto de entrada del programa.
	 * 
	 */

	/* Se declara la ventana */
	private Stage escenarioPrincipal;

	/* Se declara las escenas que va a controlar el Aplicacion */
	private Scene pantallaPrincipal;
	private Scene pantallaMenu;
	private Scene pantallaCreditos;
	private Scene stackScene;
	private Scene pantallaReglas;

	/* Declaramos las vistas */

	private Parent menuView;
	private Parent creditosView;
	private Parent principalView;
	private Parent reglasView;

	/* Declaramos los campos de textos para empezar la partida */

	private TextField campoNombreFichasNegras;
	private TextField campoNombreFichasBlancas;
	private TextField campoDimension;

	private StackPane containerPrincipal;

	private AnchorPane anchorPanePrincipal;
	private AnchorPane anchorPaneMenu;
	private AnchorPane anchorPaneCredito;
	private AnchorPane anchorPaneReglas;

	@Override
	public void start(Stage escenarioPrincipal) throws Exception {

		this.escenarioPrincipal = escenarioPrincipal;

		cargarFuentes();

		cargarVistas();

		cargarEscenas();

		cargamosElemntosDeLaVista();

		cargarBotones();

		configuramosTextos();

		personalizamosLaVentana();

		armarAnimacion();

	}

	private void cargarVistas() throws IOException {

		/* Cargamos las vistas */
		principalView = FXMLLoader.load(getClass().getResource(
				"./../views/pantalla-principal.fxml"));
		menuView = FXMLLoader.load(getClass().getResource(
				"./../views/menu.fxml"));
		creditosView = FXMLLoader.load(getClass().getResource(
				"./../views/creditos.fxml"));
		reglasView = FXMLLoader.load(getClass().getResource(
				"./../views/reglas.fxml"));
	}

	private void cargarEscenas() {

		/* Creamos las escenas */
		pantallaPrincipal = new Scene(principalView, 800, 680);
		pantallaMenu = new Scene(menuView, 800, 680);
		pantallaCreditos = new Scene(creditosView, 800, 680);
		pantallaReglas = new Scene(reglasView, 800, 680);
	}

	private void cargamosElemntosDeLaVista() {
		anchorPanePrincipal = (AnchorPane) pantallaPrincipal
				.lookup("#anchorPanePrincipal");

		anchorPaneMenu = (AnchorPane) pantallaMenu.lookup("#anchorPaneMenu");

		anchorPaneCredito = (AnchorPane) pantallaCreditos
				.lookup("#anchorPaneCredtitos");

		anchorPaneReglas = (AnchorPane) pantallaReglas
				.lookup("#anchorPaneReglas");

		containerPrincipal = new StackPane();

		containerPrincipal.getChildren().add(anchorPanePrincipal);

		stackScene = new Scene(containerPrincipal, 800, 680);

	}

	private void cargarBotones() {

		Button btnIniciar = (Button) pantallaPrincipal.lookup("#btnIniciar");
		Button btnCerrar = (Button) pantallaPrincipal.lookup("#btnCerrar");
		Button btnCerrarMenu = (Button) pantallaMenu.lookup("#btnCerrar");
		Button btnCerrarCreditos = (Button) pantallaCreditos
				.lookup("#btnCerrar");
		Button btnCerrarReglas = (Button) reglasView.lookup("#btnCerrar");
		Button btnCreditos = (Button) pantallaPrincipal.lookup("#btnCreditos");
		Button btnVolverMenu = (Button) pantallaMenu.lookup("#btnVolver");
		Button btnVolverCreditos = (Button) creditosView.lookup("#btnVolver");
		Button btnVolverReglas = (Button) reglasView.lookup("#btnVolver");
		Button btnIniciarPartida = (Button) pantallaMenu.lookup("#btnJugar");
		Button btnReglas = (Button) pantallaPrincipal.lookup("#btnReglas");

		btnCerrar.addEventHandler(MouseEvent.MOUSE_CLICKED, new CerrarJuego());
		btnCerrarMenu.addEventHandler(MouseEvent.MOUSE_CLICKED,
				new CerrarJuego());
		btnCerrarCreditos.addEventHandler(MouseEvent.MOUSE_CLICKED,
				new CerrarJuego());
		btnCerrarReglas.addEventHandler(MouseEvent.MOUSE_CLICKED,
				new CerrarJuego());

		btnReglas.addEventHandler(MouseEvent.MOUSE_CLICKED, new CambiarEscena(
				pantallaReglas, escenarioPrincipal, reglasView,
				containerPrincipal, 1, anchorPanePrincipal));

		btnCreditos.addEventHandler(MouseEvent.MOUSE_CLICKED,
				new CambiarEscena(pantallaCreditos, escenarioPrincipal,
						creditosView, containerPrincipal, 1,
						anchorPanePrincipal));

		btnIniciar.addEventHandler(MouseEvent.MOUSE_CLICKED, new CambiarEscena(
				pantallaMenu, escenarioPrincipal, menuView, containerPrincipal,
				1, anchorPanePrincipal));

		btnVolverMenu.addEventHandler(MouseEvent.MOUSE_CLICKED,
				new CambiarEscena(pantallaPrincipal, escenarioPrincipal,
						principalView, containerPrincipal, -1, anchorPaneMenu));

		btnVolverReglas
				.addEventHandler(MouseEvent.MOUSE_CLICKED, new CambiarEscena(
						pantallaPrincipal, escenarioPrincipal, principalView,
						containerPrincipal, -1, anchorPaneReglas));

		btnVolverCreditos.addEventHandler(MouseEvent.MOUSE_CLICKED,
				new CambiarEscena(pantallaPrincipal, escenarioPrincipal,
						principalView, containerPrincipal, -1,
						anchorPaneCredito));

		btnIniciarPartida.addEventHandler(MouseEvent.MOUSE_CLICKED,
				new IniciarJuego(this));
	}

	private void configuramosTextos() {
		campoNombreFichasNegras = (TextField) pantallaMenu
				.lookup("#txtJugador1");
		campoNombreFichasBlancas = (TextField) pantallaMenu
				.lookup("#txtJugador2");
		campoDimension = (TextField) pantallaMenu.lookup("#txtDimension");
		campoDimension.setText("4");
		campoNombreFichasNegras.setText("Cruces");
		campoNombreFichasBlancas.setText("Circulos");
	}

	private void personalizamosLaVentana() {
		escenarioPrincipal.initStyle(StageStyle.UNDECORATED);
		escenarioPrincipal.setScene(stackScene);
		escenarioPrincipal.getIcons().add(new Image("./img/icono.png"));
		escenarioPrincipal.setTitle("REVERSI");
		escenarioPrincipal.setResizable(false);
		escenarioPrincipal.show();

		EstilizarVentana.allowDrag(principalView, escenarioPrincipal);
		EstilizarVentana.allowDrag(menuView, escenarioPrincipal);
		EstilizarVentana.allowDrag(creditosView, escenarioPrincipal);
		EstilizarVentana.stageDimension(escenarioPrincipal.getWidth(),
				escenarioPrincipal.getHeight());
	}

	private void armarAnimacion() {
		ImageView titulo = (ImageView) principalView.lookup("#imageTitulo");
		titulo.toFront();
		TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.millis(1000));
		transition.setNode(titulo);
		transition.setToY(230);
		transition.setOnFinished(e -> {
			titulo.toBack();
		});
		transition.play();
	}

	private void cargarFuentes() {
		
		Font.loadFont(getClass().getResource("./../fonts/Oswald-Bold.ttf")
				.toExternalForm(), 10);
		Font.loadFont(getClass()
				.getResource("./../fonts/Oswald-ExtraLight.ttf")
				.toExternalForm(), 10);
		Font.loadFont(getClass().getResource("./../fonts/Oswald-Light.ttf")
				.toExternalForm(), 10);
		Font.loadFont(getClass().getResource("./../fonts/Oswald-Medium.ttf")
				.toExternalForm(), 10);
		Font.loadFont(getClass().getResource("./../fonts/Oswald-Regular.ttf")
				.toExternalForm(), 10);
		Font.loadFont(getClass().getResource("./../fonts/Oswald-SemiBold.ttf")
				.toExternalForm(), 10);

	}

	public void iniciar() throws IOException {

		String nombreFichasNegras = campoNombreFichasNegras.getText();
		String nombreFichasBlancas = campoNombreFichasBlancas.getText();
		int dimension;
		try {
			dimension = Integer.parseInt(campoDimension.getText());
		} catch (NumberFormatException e) {
			throw new Error("La dimensión debe ser un número entero");
		}

		Reversi juego = new Reversi(dimension, nombreFichasNegras,
				nombreFichasBlancas);

		Tablero tablero = new Tablero(juego, escenarioPrincipal, pantallaMenu,
				containerPrincipal, anchorPaneMenu, menuView);
		tablero.mostrar();
	}

	public static void main(String[] args) {

		Thread.setDefaultUncaughtExceptionHandler(new MostrarError());

		launch(args);
	}

}
