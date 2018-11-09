package juego;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import  javafx.scene.control.Button;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.Event;
import javafx.scene.input.MouseEvent;
import javafx.stage.StageStyle;
import java.io.IOException;

public class MenuController extends Application {

    private Stage window;
    private  Scene pantallaPrincipal;
    private  Scene pantallaMenu;
    private Scene pantallaCreditos;

    private TextField campoNombreFichasNegras;
    private TextField campoNombreFichasBlancas;

    private TextField campoDimension;

    @Override
    public void start(Stage escenarioPrincipal) throws Exception {

        window = escenarioPrincipal;


        Parent pantallaPrincipalView =  FXMLLoader.load(getClass().getResource("./../views/pantalla-principal.fxml"));
        Parent menu =  FXMLLoader.load(getClass().getResource("./../views/menu.fxml"));
        Parent creditos = FXMLLoader.load(getClass().getResource("./../views/creditos.fxml"));

        pantallaPrincipal = new Scene(pantallaPrincipalView, 450, 600);
        pantallaMenu = new Scene(menu, 450, 600);
        pantallaCreditos = new Scene(creditos, 450,800);

        Button btnIniciar = (Button) pantallaPrincipal.lookup("#btnIniciar");
        Button btnCerrar = (Button) pantallaPrincipal.lookup("#btnCerrar");
        Button btnCerrarMenu = (Button) pantallaMenu.lookup("#btnCerrar");
        Button btnCerrarCreditos = (Button) pantallaCreditos.lookup("#btnCerrar");
        Button btnCreditos = (Button) pantallaPrincipal.lookup("#btnCreditos");
        Button btnVolverMenu = (Button) pantallaMenu.lookup("#btnVolver");
        Button btnVolverCreditos = (Button) creditos.lookup("#btnVolver");
        Button btnIniciarPartida = (Button) pantallaMenu.lookup("#btnJugar");

        campoNombreFichasNegras = (TextField) pantallaMenu.lookup("#txtJugador1");
        campoNombreFichasBlancas = (TextField) pantallaMenu.lookup("#txtJugador2");
        campoDimension = (TextField) pantallaMenu.lookup("#txtDimension");

        btnCerrar.addEventHandler(MouseEvent.MOUSE_CLICKED, new Cerrar());
        btnCerrarMenu.addEventHandler(MouseEvent.MOUSE_CLICKED, new Cerrar());
        btnCerrarCreditos.addEventHandler(MouseEvent.MOUSE_CLICKED, new Cerrar());
        btnCreditos.addEventHandler(MouseEvent.MOUSE_CLICKED, new AbrirMenuCreditos());
        btnIniciar.addEventHandler(MouseEvent.MOUSE_CLICKED, new AbrirMenu());
        btnVolverMenu.addEventHandler(MouseEvent.MOUSE_CLICKED, new VolverAInicio());
        btnVolverCreditos.addEventHandler(MouseEvent.MOUSE_CLICKED, new VolverAInicio());

        btnIniciarPartida.addEventHandler(MouseEvent.MOUSE_CLICKED, new IniciarJuego());

        escenarioPrincipal.initStyle(StageStyle.UNDECORATED);
        escenarioPrincipal.setScene(pantallaPrincipal);
        escenarioPrincipal.setResizable(false);
        escenarioPrincipal.show();
        WindowStyle.allowDrag(pantallaPrincipalView, escenarioPrincipal);
        WindowStyle.allowDrag(menu, escenarioPrincipal);
        WindowStyle.allowDrag(creditos, escenarioPrincipal);
        WindowStyle.stageDimension(escenarioPrincipal.getWidth(), escenarioPrincipal.getHeight());

    }

    public void iniciar() {

        String nombreFichasNegras = campoNombreFichasNegras.getText();
        String nombreFichasBlancas = campoNombreFichasBlancas.getText();
        int dimension = Integer.parseInt(campoDimension.getText());

        Reversi juego = new Reversi(dimension, nombreFichasNegras, nombreFichasBlancas);

        Tablero tablero = new Tablero(juego);
        tablero.mostrar();
    }


    public static void main(String[] args) throws IOException {

        Thread.setDefaultUncaughtExceptionHandler(new MostrarError());

        launch(args);
    }

    private class IniciarJuego implements EventHandler<Event> {
        @Override
        public void handle(Event evt) {
            iniciar();
        }
    }

    private class AbrirMenu implements EventHandler<Event> {
        @Override
        public void handle(Event evt) {
            window.setScene(pantallaMenu);
        }
    }

    private class AbrirMenuCreditos implements EventHandler<Event> {
        @Override
        public void handle(Event evt) {
            window.setScene(pantallaCreditos);
        }
    }

    private class Cerrar implements EventHandler<Event> {
        @Override
        public void handle(Event evt) {
            Platform.exit();
            System.exit(0);
        }
    }
    private class VolverAInicio implements EventHandler<Event> {
        @Override
        public void handle(Event evt) {
            window.setScene(pantallaPrincipal);
        }
    }
}
