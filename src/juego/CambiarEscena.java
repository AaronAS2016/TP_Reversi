package juego;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CambiarEscena implements EventHandler<Event> {

    private Stage escenario;
    private Scene escenaNueva;

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
    @Override
    public void handle(Event event) {
        escenario.setScene(escenaNueva);
    }
}
