package juego;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;

public class Aplicacion extends Application {

	/*Se declara la ventana*/
	private Stage escenarioPrincipal;

	/*Se declara las escenas que va a controlar el Aplicacion*/
	private Scene pantallaPrincipal;
	private Scene pantallaMenu;
	private Scene pantallaCreditos;
	private Scene stackScene;

	/*Declaramos las vistas*/

	private Parent menuView;
	private Parent creditosView;
	private Parent principalView;

	/*Declaramos los campos de textos para empezar la partida*/

	private TextField campoNombreFichasNegras;
	private TextField campoNombreFichasBlancas;
	private TextField campoDimension;

	private StackPane containerPrincipal;

	private AnchorPane anchorPanePrincipal;
	private AnchorPane anchorPaneMenu;
	private AnchorPane anchorPaneCredito;

	@Override
	public void start(Stage escenarioPrincipal) throws Exception {

		this.escenarioPrincipal = escenarioPrincipal;

		cargarVistas();

		cargarEscenas();

		cargamosElemntosDeLaVista();

		cargarBotones();

		configuramosTextos();

		personalizamosLaVentana();


		armarAnimacion();

	}

	private void cargamosElemntosDeLaVista() {
		anchorPanePrincipal = (AnchorPane) pantallaPrincipal.lookup("#anchorPanePrincipal");

		anchorPaneMenu = (AnchorPane) pantallaMenu.lookup("#anchorPaneMenu");

		anchorPaneCredito = (AnchorPane) pantallaCreditos.lookup("#anchorPaneCredtitos");

		containerPrincipal = new StackPane();

		containerPrincipal.getChildren().add(anchorPanePrincipal);

		stackScene = new Scene(containerPrincipal, 450, 600);


	}

	private void armarAnimacion() {
		ImageView titulo = (ImageView) principalView.lookup("#imageTitulo");
		titulo.toFront();
		TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.seconds(1));
		transition.setNode(titulo);
		transition.setToY(230);
		transition.setOnFinished(e->{
			titulo.toBack();
		});
		transition.play();
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

	private void cargarBotones() {



		Button btnIniciar = (Button) pantallaPrincipal.lookup("#btnIniciar");
		Button btnCerrar = (Button) pantallaPrincipal.lookup("#btnCerrar");
		Button btnCerrarMenu = (Button) pantallaMenu.lookup("#btnCerrar");
		Button btnCerrarCreditos = (Button) pantallaCreditos
				.lookup("#btnCerrar");
		Button btnCreditos = (Button) pantallaPrincipal.lookup("#btnCreditos");
		Button btnVolverMenu = (Button) pantallaMenu.lookup("#btnVolver");
		Button btnVolverCreditos = (Button) creditosView.lookup("#btnVolver");
		Button btnIniciarPartida = (Button) pantallaMenu.lookup("#btnJugar");

		btnCerrar.addEventHandler(MouseEvent.MOUSE_CLICKED, new CerrarJuego());
		btnCerrarMenu.addEventHandler(MouseEvent.MOUSE_CLICKED, new CerrarJuego());
		btnCerrarCreditos.addEventHandler(MouseEvent.MOUSE_CLICKED, new CerrarJuego());

		btnCreditos.addEventHandler(MouseEvent.MOUSE_CLICKED, new CambiarEscena(pantallaCreditos, escenarioPrincipal, creditosView, containerPrincipal, 1, anchorPanePrincipal, true));

		btnIniciar.addEventHandler(MouseEvent.MOUSE_CLICKED, new CambiarEscena(pantallaMenu, escenarioPrincipal, menuView, containerPrincipal, 1, anchorPanePrincipal));

		btnVolverMenu.addEventHandler(MouseEvent.MOUSE_CLICKED,  new CambiarEscena(pantallaPrincipal, escenarioPrincipal, principalView, containerPrincipal, -1, anchorPaneMenu));

		btnVolverCreditos.addEventHandler(MouseEvent.MOUSE_CLICKED, new CambiarEscena(pantallaPrincipal, escenarioPrincipal, principalView, containerPrincipal, -1, anchorPaneCredito, true));

		btnIniciarPartida.addEventHandler(MouseEvent.MOUSE_CLICKED, new IniciarJuego(this));
	}

	private void cargarEscenas() {
		/*Creamos las escenas*/
		pantallaPrincipal = new Scene(principalView, 450, 600);
		pantallaMenu = new Scene(menuView, 450, 600);
		pantallaCreditos = new Scene(creditosView, 450, 680);
	}

	private void cargarVistas() throws IOException {


		/*Cargamos las vistas*/
		principalView = FXMLLoader.load(getClass().getResource(
				"./../views/pantalla-principal.fxml"));
		menuView = FXMLLoader.load(getClass().getResource("./../views/menu.fxml"));
		creditosView = FXMLLoader.load(getClass().getResource(
				"./../views/creditos.fxml"));
	}

	public void iniciar() throws IOException {

		String nombreFichasNegras = campoNombreFichasNegras.getText();
		String nombreFichasBlancas = campoNombreFichasBlancas.getText();
		int dimension = Integer.parseInt(campoDimension.getText());

		Reversi juego = new Reversi(dimension, nombreFichasNegras,
				nombreFichasBlancas);

		Tablero tablero = new Tablero(juego, escenarioPrincipal,
				pantallaMenu);
		tablero.mostrar();
	}

	public static void main(String[] args) {

		Thread.setDefaultUncaughtExceptionHandler(new MostrarError());

		launch(args);
	}

}
