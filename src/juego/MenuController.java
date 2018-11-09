package juego;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import  javafx.scene.control.Button;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

    @Override
    public void start(Stage escenarioPrincipal) throws Exception {

        window = escenarioPrincipal;


        Parent pantallaPrincipalView =  FXMLLoader.load(getClass().getResource("./../views/pantalla-principal.fxml"));
        Parent menu =  FXMLLoader.load(getClass().getResource("./../views/menu.fxml"));
        Parent creditos = FXMLLoader.load(getClass().getResource("./../views/creditos.fxml"));

        pantallaPrincipal = new Scene(pantallaPrincipalView, 450, 600);
        pantallaMenu = new Scene(menu, 450, 600);
        pantallaCreditos = new Scene(creditos, 450,600);

        Button btnIniciar = (Button) pantallaPrincipal.lookup("#btnIniciar");
        Button btnCerrar = (Button) pantallaPrincipal.lookup("#btnCerrar");
        Button btnCerrarMenu = (Button) pantallaMenu.lookup("#btnCerrar");
        Button btnCerrarCreditos = (Button) pantallaCreditos.lookup("#btnCerrar");
        Button btnCreditos = (Button) pantallaPrincipal.lookup("#btnCreditos");
        Button btnVolverMenu = (Button) pantallaMenu.lookup("#btnVolver");
        Button btnVolverCreditos = (Button) creditos.lookup("#btnVolver");

        btnCerrar.addEventHandler(MouseEvent.MOUSE_CLICKED, new Cerrar());
        btnCerrarMenu.addEventHandler(MouseEvent.MOUSE_CLICKED, new Cerrar());
        btnCerrarCreditos.addEventHandler(MouseEvent.MOUSE_CLICKED, new Cerrar());
        btnCreditos.addEventHandler(MouseEvent.MOUSE_CLICKED, new AbrirMenuCreditos());
        btnIniciar.addEventHandler(MouseEvent.MOUSE_CLICKED, new AbrirMenu());
        btnVolverMenu.addEventHandler(MouseEvent.MOUSE_CLICKED, new VolverAInicio());
        btnVolverCreditos.addEventHandler(MouseEvent.MOUSE_CLICKED, new VolverAInicio());


        escenarioPrincipal.initStyle(StageStyle.UNDECORATED);
        escenarioPrincipal.setScene(pantallaPrincipal);
        escenarioPrincipal.setResizable(false);
        escenarioPrincipal.show();
        WindowStyle.allowDrag(pantallaPrincipalView, escenarioPrincipal);
        WindowStyle.allowDrag(menu, escenarioPrincipal);
        WindowStyle.allowDrag(creditos, escenarioPrincipal);
        WindowStyle.stageDimension(escenarioPrincipal.getWidth(), escenarioPrincipal.getHeight());

    }


    public static void main(String[] args) throws IOException {

        Thread.setDefaultUncaughtExceptionHandler(new MostrarError());

        launch(args);
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
