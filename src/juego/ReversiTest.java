package juego;

import static org.junit.Assert.*;

import org.junit.Test;

public class ReversiTest {

	@Test
	public void obtenerFilaConUnTablero4x4() {
		Reversi juego = new Reversi(4, "blancas", "negras");
		assertEquals(4, juego.contarFilas());
	}
	
	@Test
	public void obtenerColumnaConUnTablero4x4() {
		Reversi juego = new Reversi(4, "blancas", "negras");
		assertEquals(4, juego.contarColumnas());
	}

}
