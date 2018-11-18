package juego;

import javafx.event.Event;
import javafx.event.EventHandler;

import java.io.IOException;

public class IniciarJuego implements EventHandler<Event> {

	private Aplicacion aplicacion;

	public IniciarJuego(Aplicacion aplicacion) {

		this.aplicacion = aplicacion;
	}

	@Override
	public void handle(Event event) {
		try {
			aplicacion.iniciar();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
