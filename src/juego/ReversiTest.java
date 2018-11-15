package juego;

import static org.junit.Assert.*;

import org.junit.Test;

public class ReversiTest {

	@Test
	public void obtenerFilaConUnTablero4x4() {
		Reversi juego = new Reversi(4, "blancas", "negras");
		assertEquals(4, juego.contarFilas());
	}
	
	@Test ( expected = Error.class )
	public void obtenerFilaConUnTablero12x12 (){
		new Reversi ( 12, "blacas", "negras" );
	}
	
	@Test ( expected = Error.class )
	public void obtenerFilaConUnTablero0x0 (){
		new Reversi ( 0, "blacas", "negras" );
	}
	
	@Test ( expected = Error.class )
	public void obtenerFilaConUnTablero11x11 (){
		new Reversi ( 11, "blacas", "negras" );
	}

	
	@Test
	public void obtenerFilaConUnTablero10x10 (){
		Reversi juego = new Reversi ( 10, "blacas", "negras" );
		assertEquals ( 10, juego.contarFilas() );
	}

	
	@Test ( expected = Error.class )
	public void obtenerFilaConUnTablero2 (){
		new Reversi ( 2, "blacas", "negras" );
	}

	
	@Test ( expected = Error.class )
	public void obtenerFilaConUnTablero3 (){
		new Reversi ( 3, "blacas", "negras" );
	}

	
	@Test ( expected = Error.class )
	public void obtenerFilaConUnTablero9x9 (){
		new Reversi ( 9, "blacas", "negras" );
	}


	@Test ( expected = Error.class )
	public void obtenerFilaConUnTablero7x7 (){
		new Reversi ( 7, "blacas", "negras" );
	}

	
	
	@Test ( expected = Error.class )
	public void obtenerFilaConUnTablero5x5 (){
		new Reversi ( 5, "blacas", "negras" );
	}

	@Test ( expected = Error.class )
	public void obtenerColumnaConUnTablero0x0() {
		new Reversi(0, "blancas", "negras");
	}
	
	@Test ( expected = Error.class )
	public void obtenerColumnaConUnTablero2x2() {
		new Reversi(2, "blancas", "negras");
	}
	
	@Test ( expected = Error.class )
	public void obtenerColumnaConUnTablero3x3() {
		new Reversi(3, "blancas", "negras");
	}
	
	@Test ( expected = Error.class )
	public void obtenerColumnaConUnTablero5x5() {
		new Reversi(5, "blancas", "negras");
	}
	
	@Test ( expected = Error.class )
	public void obtenerColumnaConUnTablero11x11() {
		new Reversi(11, "blancas", "negras");
	}
	
	@Test ( expected = Error.class )
	public void obtenerColumnaConUnTablero12x12() {
		new Reversi(12, "blancas", "negras");
	}
	
	@Test
	public void obtenerColumnaConUnTablero4x4() {
		Reversi juego = new Reversi(4, "blancas", "negras");
		assertEquals(4, juego.contarColumnas());
	}

	@Test
	public void terminarPartidaEnEmpateConUnTablero4x4() {
		Reversi juego = new Reversi(4, "blancas", "negras");
		juego.colocarFicha(2, 4);
		juego.colocarFicha(3, 4);
		juego.colocarFicha(4, 3);
		juego.colocarFicha(1, 4);
		juego.colocarFicha(4, 2);
		juego.colocarFicha(2, 1);
		juego.colocarFicha(1, 1);
		juego.colocarFicha(3, 1);
		juego.colocarFicha(4, 1);
		juego.colocarFicha(1, 2);
		juego.colocarFicha(1, 3);
		assertTrue(juego.termino());
	}

	@Test(expected = Error.class)
	public void colocarFichaEnCasilleroOcupado() {
		Reversi juego = new Reversi(4, "blancas", "negras");
		juego.colocarFicha(2, 3);
		juego.colocarFicha(2, 3);
	}

	@Test(expected = Error.class)
	public void colocarFichaEnFila0() {
		Reversi juego = new Reversi(4, "blancas", "negras");
		juego.colocarFicha(0, 4);
	}

	@Test(expected = Error.class)
	public void validarTableroDimensionNegativa() {
		new Reversi(-4, "blancas", "negras");
	}

	@Test(expected = Error.class)
	public void validarTableroDimension0() {
		new Reversi(0, "blancas", "negras");
	}

	@Test(expected = Error.class)
	public void validarTableroDimension3x3() {
		new Reversi(3, "blancas", "negras");
	}

	@Test(expected = Error.class)
	public void validarTableroDimension7x7() {
		new Reversi(7, "blancas", "negras");
	}

	@Test(expected = Error.class)
	public void validarTableroDimension5x5() {
		new Reversi(5, "blancas", "negras");
	}

	@Test(expected = Error.class)
	public void validarTableroDimension11x11() {
		new Reversi(11, "blancas", "negras");
	}

	@Test(expected = Error.class)
	public void validarTableroDimension12x12() {
		new Reversi(12, "blancas", "negras");
	}

	@Test(expected = Error.class)
	public void validarTableroDimension2x2() {
		new Reversi(2, "blancas", "negras");
	}

	@Test
	public void validarTableroDimension6x6() {
		new Reversi(6, "blancas", "negras");
	}

	@Test
	public void validarTableroDimension8x8() {
		new Reversi(8, "blancas", "negras");
	}

	@Test
	public void validarTableroDimension10x10() {
		new Reversi(10, "blancas", "negras");
	}

	@Test(expected = Error.class)
	public void iniciarPartidaConNombresConEspacio() {
		new Reversi(6, "blancas", "neg ras");
	}

	@Test(expected = Error.class)
	public void iniciarPartidaConNombreSoloDeEspacio() {
		new Reversi(6, "blancas", "          ");
	}

	@Test(expected = Error.class)
	public void iniciarPartidaConNombreCon11Caracteres() {
		new Reversi ( 6, "blancas", "11caracters" ); 
	}
	
	@Test ( expected = Error.class )
	public void iniciarPartidaConNombreCon0Caracteres () {
		new Reversi (4, "blancas", "");
	}
	
	@Test
	public void iniciarPartidaConNombresCon3Caracteres () {
		new Reversi ( 4, "blancas", "Fer" );
	}
	
	@Test (expected = Error.class)
	public void iniciarPartidaConNombreCon2Caracteres() {
		new Reversi ( 4,"blancas", "Mi" );
	}
	
	@Test ( expected = Error.class )
	public void obtenerNombreDeJugador4 () {
		Reversi juego = new Reversi ( 6,"blancas", "Mich" );
		juego.obtenerJugadores ( 4 );
	}
	
	@Test ( expected = Error.class )
	public void obtenerNombreDeJugador0 () {
		Reversi juego = new Reversi ( 6, "blancas", "Mich" );
		juego.obtenerJugadores(0);
	}
	
	@Test
	public void obtenerNombreDeJugador1 () {
		Reversi juego = new Reversi ( 6, "blancas", "Mich" );
		assertEquals ( "blancas", juego.obtenerJugadores(1) );
	}
	
	@Test
	public void obtenerNombreDeJugador2 () {
		Reversi juego = new Reversi ( 6, "blancas", "Mich" );
		assertEquals ( "Mich", juego.obtenerJugadores (2) );
	}
	
	@Test ( expected = Error.class )
	public void obtenerNombreDeJugadorMenos1 () {
		Reversi juego = new Reversi ( 6, "blancas", "Mich" );
		juego.obtenerJugadores (-1);
	}
	

	
	
	
	
	
}
