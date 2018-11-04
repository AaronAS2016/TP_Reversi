package juego;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AplicacionV2 extends Application {



    @Override
    public void start(Stage escenarioPrincipal) throws Exception {

        Parent root =  FXMLLoader.load(getClass().getResource("./../views/pantalla-principal.fxml"));

        Scene escena = new Scene(root, 450, 600);
        //btnIniciar.setOnAction(event -> checkID())
        escenarioPrincipal.setScene(escena);
        escenarioPrincipal.setTitle("REVERSI");
        escenarioPrincipal.show();

    }


    public static void main(String[] args) throws IOException {

        Thread.setDefaultUncaughtExceptionHandler(new MostrarError());

        launch(args);
    }
}
