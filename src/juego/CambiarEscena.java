package juego;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;


public class CambiarEscena implements EventHandler<Event> {

    @FXML
    private StackPane container;

    @FXML
    private Stage escenario;

    @FXML
    private Scene escenaNueva;

    @FXML
    private Parent vista;

    @FXML
    private AnchorPane vistaABorrar;

    private double direccion;

    private boolean cambiarAncho;


    /**
     * post: cambia la escena recibida al escenario que se pasa luego de ejecutar un evento
     *
     * @param escenario
     * @param escenaNueva
     */
    public CambiarEscena(Scene escenaNueva, Stage escenario){
        this.escenario = escenario;
        this.escenaNueva = escenaNueva;
    }

    public CambiarEscena(Scene escenaNueva, Stage escenario, Parent vista, StackPane container, double direccion, AnchorPane vistaABorrar){
        this.escenario = escenario;
        this.escenaNueva = escenaNueva;
        this.vista = vista;
        this.container = container;
        this.direccion = direccion;
        this.vistaABorrar = vistaABorrar;
    }

    @Override
    public void handle(Event event) {

        cambiarEscena();


    }

    public void cambiarEscena() {
        if(this.direccion == 0){
            escenario.setScene(escenaNueva);
        }else{
            // escenario.setScene(escenaNueva);
            double ancho = escenaNueva.getWidth() * direccion;
            vista.translateXProperty().set(ancho);
            container.getChildren().add(vista);

            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(vista.translateXProperty(), 0 , Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.millis(500), kv);
            timeline.getKeyFrames().add(kf);
            timeline.setOnFinished(event1 -> {
                container.getChildren().remove(vistaABorrar);
            });
            timeline.play();
        }
    }
}
