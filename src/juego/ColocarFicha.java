package juego;

import javafx.event.Event;
import javafx.event.EventHandler;


/**
 * Acci�n ejecutada al presionar un bot�n para colocar una ficha en 
 * un casillero del tablero.
 * 
 */
public class ColocarFicha implements EventHandler<Event> {
	private Tablero tablero;
	private Reversi juego;
	private int fila;
	private int columna;
	public ColocarFicha(Tablero tableroReversi, Reversi reversi,
						int filaSeleccionada, int columnaSeleccionada) {

		tablero = tableroReversi;
		juego = reversi;
		fila = filaSeleccionada;
		columna = columnaSeleccionada;
	}
	@Override
	public void handle(Event evt) {
		juego.colocarFicha(fila, columna);

		tablero.dibujar();

		if (juego.termino()) {
			tablero.mostrarResultado();
		}
	}
}
