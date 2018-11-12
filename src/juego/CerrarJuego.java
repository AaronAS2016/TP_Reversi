package juego;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;

public class CerrarJuego  implements EventHandler<Event> {
    @Override
    public void handle(Event evt) {
        Platform.exit();
        System.exit(0);
    }
}
