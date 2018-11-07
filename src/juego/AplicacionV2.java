package juego;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import  javafx.scene.control.Button;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.awt.*;
import java.io.IOException;

public class AplicacionV2 extends Application {

    private Stage window;
    private  Scene pantallaPrincipal;
    private  Scene pantallaMenu;

    @Override
    public void start(Stage escenarioPrincipal) throws Exception {

        window = escenarioPrincipal;

        Parent pantallaPrincipalView =  FXMLLoader.load(getClass().getResource("./../views/pantalla-principal.fxml"));
        Parent menu =  FXMLLoader.load(getClass().getResource("./../views/menu.fxml"));

        pantallaPrincipal = new Scene(pantallaPrincipalView, 450, 600);
        pantallaMenu = new Scene(menu, 450, 600);

        Button btnIniciar = (Button) pantallaPrincipal.lookup("#btnIniciar");
       btnIniciar.addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
       //btnIniciar.setOnAction();


        escenarioPrincipal.setScene(pantallaPrincipal);
        escenarioPrincipal.setTitle("REVERSI");
        escenarioPrincipal.show();

    }


    public static void main(String[] args) throws IOException {

        Thread.setDefaultUncaughtExceptionHandler(new MostrarError());

        launch(args);
    }

    private class MyEventHandler implements EventHandler<Event> {
        @Override
        public void handle(Event evt) {
            window.setScene(pantallaMenu);
        }
    }
}
