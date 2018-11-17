package juego;

import static org.junit.Assert.*;

import org.junit.Test;

public class ReversiTest {

	//Metodo obtener fila / columna
	
	@Test
	public void obtenerFilaConUnTablero4x4() {
		Reversi juego = new Reversi(4, "blancas", "negras");
		assertEquals(4, juego.contarFilas());
	}

	@Test(expected = Error.class)
	public void obtenerFilaConUnTablero12x12() {
		new Reversi(12, "blacas", "negras");
	}

	@Test(expected = Error.class)
	public void obtenerFilaConUnTablero0x0() {
		new Reversi(0, "blacas", "negras");
	}

	@Test(expected = Error.class)
	public void obtenerFilaConUnTablero11x11() {
		new Reversi(11, "blacas", "negras");
	}

	@Test
	public void obtenerFilaConUnTablero10x10() {
		Reversi juego = new Reversi(10, "blacas", "negras");
		assertEquals(10, juego.contarFilas());
	}

	@Test(expected = Error.class)
	public void obtenerFilaConUnTablero2() {
		new Reversi(2, "blacas", "negras");
	}

	@Test(expected = Error.class)
	public void obtenerFilaConUnTablero3() {
		new Reversi(3, "blacas", "negras");
	}

	@Test(expected = Error.class)
	public void obtenerFilaConUnTablero9x9() {
		new Reversi(9, "blacas", "negras");
	}

	@Test(expected = Error.class)
	public void obtenerFilaConUnTablero7x7() {
		new Reversi(7, "blacas", "negras");
	}

	@Test(expected = Error.class)
	public void obtenerFilaConUnTablero5x5() {
		new Reversi(5, "blacas", "negras");
	}

	@Test(expected = Error.class)
	public void obtenerColumnaConUnTablero0x0() {
		new Reversi(0, "blancas", "negras");
	}

	@Test(expected = Error.class)
	public void obtenerColumnaConUnTablero2x2() {
		new Reversi(2, "blancas", "negras");
	}

	@Test(expected = Error.class)
	public void obtenerColumnaConUnTablero3x3() {
		new Reversi(3, "blancas", "negras");
	}

	@Test(expected = Error.class)
	public void obtenerColumnaConUnTablero5x5() {
		new Reversi(5, "blancas", "negras");
	}

	@Test(expected = Error.class)
	public void obtenerColumnaConUnTablero11x11() {
		new Reversi(11, "blancas", "negras");
	}

	@Test(expected = Error.class)
	public void obtenerColumnaConUnTablero12x12() {
		new Reversi(12, "blancas", "negras");
	}

	@Test
	public void obtenerColumnaConUnTablero4x4() {
		Reversi juego = new Reversi(4, "blancas", "negras");
		assertEquals(4, juego.contarColumnas());
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

	@Test
	public void iniciarPartidaConNombresConEspacio() {
		Reversi juego = new Reversi(6, "blancas", "n egras");
		assertEquals("negras", juego.obtenerJugadores(2));
	}

	@Test(expected = Error.class)
	public void iniciarPartidaConNombreSoloDeEspacio() {
		new Reversi(6, "blancas", "          ");
	}

	@Test(expected = Error.class)
	public void iniciarPartidaConNombreCon11Caracteres() {
		new Reversi(6, "blancas", "11caracters");
	}

	@Test(expected = Error.class)
	public void iniciarPartidaConNombreCon0Caracteres() {
		new Reversi(4, "blancas", "");
	}

	@Test
	public void iniciarPartidaConNombresCon3Caracteres() {
		new Reversi(4, "blancas", "Fer");
	}

	@Test(expected = Error.class)
	public void iniciarPartidaConNombreCon2Caracteres() {
		new Reversi(4, "blancas", "Mi");
	}

	@Test(expected = Error.class)
	public void obtenerNombreDeJugador4() {
		Reversi juego = new Reversi(6, "blancas", "Mich");
		juego.obtenerJugadores(4);
	}

	@Test(expected = Error.class)
	public void obtenerNombreDeJugador0() {
		Reversi juego = new Reversi(6, "blancas", "Mich");
		juego.obtenerJugadores(0);
	}

	@Test
	public void obtenerNombreDeJugador1() {
		Reversi juego = new Reversi(6, "blancas", "Mich");
		assertEquals("blancas", juego.obtenerJugadores(1));
	}

	@Test
	public void obtenerNombreDeJugador2() {
		Reversi juego = new Reversi(6, "blancas", "Mich");
		assertEquals("Mich", juego.obtenerJugadores(2));
	}

	@Test(expected = Error.class)
	public void obtenerNombreDeJugadorMenos1() {
		Reversi juego = new Reversi(6, "blancas", "Mich");
		juego.obtenerJugadores(-1);
	}

	// Metodo contarFichas Negras/Blancas/Ocupadas

	@Test
	public void contarFichasNegrasAlInicioDeLaPartidaConTablero4x4() {
		Reversi juego = new Reversi(4, "blancas", "negras");
		assertEquals(2, juego.contarFichasNegras());
	}

	@Test
	public void contarFichasBlancasAlInicioDeLaPartidaConTablero4x4() {
		Reversi juego = new Reversi(4, "blancas", "negras");
		assertEquals(2, juego.contarFichasBlancas());
	}

	@Test
	public void contarFichasOcupadasAlInicioDeLaPartidaConTablero4x4() {
		Reversi juego = new Reversi(4, "blancas", "negras");
		assertEquals(4, juego.contarFichasOcupadas());
	}

	@Test
	public void contarFichasOcupadasEnTablero4x4Sumando1Ficha() {
		Reversi juego = new Reversi(4, "blancas", "negras");
		juego.colocarFicha(2, 4);
		assertEquals(5, juego.contarFichasOcupadas());
	}
	
	@Test
	public void contarFichasOcupadasEnTablero4x4Sumando2Fichas() {
		Reversi juego = new Reversi(4, "blancas", "negras");
		juego.colocarFicha(2, 4);
		juego.colocarFicha(1, 2);
		assertEquals(6, juego.contarFichasOcupadas());
	}
	
	@Test
	public void contarFichasNegrasEnTablero4x4() {
		Reversi juego = new Reversi(4, "blancas", "negras");
		juego.colocarFicha(2, 4);
		juego.colocarFicha(3, 4);
		assertEquals(3, juego.contarFichasNegras());
	}
	@Test
	public void contarFichasBlancasEnTablero4x4() {
		Reversi juego = new Reversi(4, "blancas", "negras");
		juego.colocarFicha(2, 4);
		juego.colocarFicha(3, 4);
		assertEquals(3, juego.contarFichasBlancas());
	}
	
	@Test
	public void contarFichasOcupadasEnTablero4x4Sumando3Fichas() {
		Reversi juego = new Reversi(4, "blancas", "negras");
		juego.colocarFicha(2, 4);
		juego.colocarFicha(1, 2);
		juego.colocarFicha(4, 1);
		assertEquals(7, juego.contarFichasOcupadas());
	}
	
	//metodo contarMovimientosPosibles
	
	@Test
	public void contarMovimientosPosiblesAlInicioDeLaPartida() {
		Reversi juego = new Reversi(4, "blancas", "negras");
		assertEquals( 4, juego .contarMovimientosPosibles());
	}
	
	@Test
	public void contarMovimientosPosiblesAlInicioDelTurnoDelSegundoJugador() {
		Reversi juego = new Reversi(4, "blancas", "negras");
		juego.colocarFicha(2, 4);
		assertEquals( 3, juego .contarMovimientosPosibles());
	}
	
	@Test
	public void contarMovimientosPosiblesEnCon6FichasNegrasY6Blancas() {
		Reversi juego = new Reversi(4, "blancas", "negras");
		juego.colocarFicha(4, 2);
		juego.colocarFicha(4, 3);
		assertEquals( 4, juego .contarMovimientosPosibles());
	}
	
	@Test
	public void contarMovimientosPosiblesConTableroLleno () {
		Reversi juego = new Reversi(4, "blancas", "negras");
		juego.colocarFicha(1, 3);
		juego.colocarFicha(1, 2);
		juego.colocarFicha(1, 1);
		juego.colocarFicha(1, 4);
		juego.colocarFicha(2, 4);
		juego.colocarFicha(3, 4);
		juego.colocarFicha(4, 4);
		juego.colocarFicha(2, 1);
		juego.colocarFicha(3, 1);
		juego.colocarFicha(4, 1);
		juego.colocarFicha(4, 2);
		juego.colocarFicha(4, 3);
		assertEquals( 0, juego .contarMovimientosPosibles());
	}
	
	@Test
	public void contarMovimientosPosiblesCuandoAmbosJugadoresSeQuedanSinMovimientos() {
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
		assertEquals( 0, juego .contarMovimientosPosibles());
	}
	
	@Test
	public void contarMovimientosPosiblesParaCrucesSinPosibilidad () {
		Reversi juego = new Reversi(4, "blancas", "negras");
		juego.colocarFicha(1, 3);
		juego.colocarFicha(1, 2);
		juego.colocarFicha(3, 1);
		juego.colocarFicha(1, 4);
		assertEquals( 0, juego .contarMovimientosPosibles());
	}
	
	@Test
	public void contarMovimientosPosiblesParaCirculosSinPosibilidad () {
		Reversi juego = new Reversi(4, "blancas", "negras");
		juego.colocarFicha(1, 3);
		juego.colocarFicha(1, 2);
		juego.colocarFicha(3, 1);
		juego.colocarFicha(1, 4);
		juego.colocarFicha(3, 4);
		juego.colocarFicha(2, 4);
		juego.colocarFicha(4, 3);
		juego.colocarFicha(4, 4);
		juego.colocarFicha(2, 1);
		juego.colocarFicha(1, 1);
		assertEquals( 0, juego .contarMovimientosPosibles());
	}
	
	// metodo colocarFicha
	
	@Test
	public void verificarColocarFichaEnFila2Columna4() {
		Reversi juego = new Reversi(4, "blancas", "negras");
		assertEquals(true, juego.puedeColocarFicha(2, 4));
	}

	@Test
	public void verificarColocarFichaEnFila1Columna3() {
		Reversi juego = new Reversi(4, "blancas", "negras");
		assertEquals(true, juego.puedeColocarFicha(1, 3));
	}

	@Test
	public void verificarColocarFichaEnFila3Columna1() {
		Reversi juego = new Reversi(4, "blancas", "negras");
		assertEquals(true, juego.puedeColocarFicha(3, 1));
	}

	@Test
	public void verificarColocarFichaEnFila4Columna2() {
		Reversi juego = new Reversi(4, "blancas", "negras");
		assertEquals(true, juego.puedeColocarFicha(4, 2));
	}

	@Test(expected = Error.class)
	public void verificarColocarFichaEnFila5Columna1EnTablero4x4() {
		Reversi juego = new Reversi(4, "blancas", "negras");
		assertEquals(false, juego.puedeColocarFicha(5, 1));
	}

	@Test(expected = Error.class)
	public void verificarColocarFichaEnFila1Columna5EnTablero4x4() {
		Reversi juego = new Reversi(4, "blancas", "negras");
		assertEquals(false, juego.puedeColocarFicha(1, 5));
	}

	@Test(expected = Error.class)
	public void verificarColocarFichaEnFila5Columna5EnTablero4x4() {
		Reversi juego = new Reversi(4, "blancas", "negras");
		assertEquals(false, juego.puedeColocarFicha(5, 5));
	}
	
	@Test ( expected = Error.class )
	public void verificarColocarFichaEnFila1Columna0EnTablero4x4 () {
		Reversi juego = new Reversi(4, "blancas", "negras");
		assertEquals(false, juego.puedeColocarFicha(1, 0));
	}
	
	@Test ( expected = Error.class )
	public void verificarColocarFichaEnFila0Columna1EnTablero4x4 () {
		Reversi juego = new Reversi(4, "blancas", "negras");
		assertEquals(false, juego.puedeColocarFicha(0, 1));
	}
	
	@Test ( expected = Error.class )
	public void verificarColocarFichaEnFila1Co0umna0EnTablero4x4 () {
		Reversi juego = new Reversi(4, "blancas", "negras");
		assertEquals(false, juego.puedeColocarFicha(0, 0));
	}

	@Test(expected = Error.class)
	public void verificarColocarFichaEnFila7Columna1EnTablero6x6() {
		Reversi juego = new Reversi(6, "blancas", "negras");
		assertEquals(false, juego.puedeColocarFicha(7, 1));
	}

	@Test(expected = Error.class)
	public void verificarColocarFichaEnFila1Columna7EnTablero6x6() {
		Reversi juego = new Reversi(6, "blancas", "negras");
		assertEquals(false, juego.puedeColocarFicha(1, 7));
	}

	@Test(expected = Error.class)
	public void verificarColocarFichaEnFila7Columna7EnTablero6x6() {
		Reversi juego = new Reversi(6, "blancas", "negras");
		assertEquals(false, juego.puedeColocarFicha(7, 7));
	}
	
	@Test ( expected = Error.class )
	public void verificarColocarFichaEnFila1Columna0EnTablero6x6 () {
		Reversi juego = new Reversi(6, "blancas", "negras");
		assertEquals(false, juego.puedeColocarFicha(0, 1));
	}

	@Test ( expected = Error.class )
	public void verificarColocarFichaEnFila0Columna1EnTablero6x6 () {
		Reversi juego = new Reversi(6, "blancas", "negras");
		assertEquals(false, juego.puedeColocarFicha(0, 1));
	}
	
	@Test ( expected = Error.class )
	public void verificarColocarFichaEnFila0Columna0EnTablero6x6 () {
		Reversi juego = new Reversi(6, "blancas", "negras");
		assertEquals(false, juego.puedeColocarFicha(0, 0));
	}
	
	@Test(expected = Error.class)
	public void verificarColocarFichaEnFila9Columna1EnTablero8x8() {
		Reversi juego = new Reversi(8, "blancas", "negras");
		assertEquals(false, juego.puedeColocarFicha(9, 1));
	}

	@Test(expected = Error.class)
	public void verificarColocarFichaEnFila1Columna9EnTablero8x8() {
		Reversi juego = new Reversi(8, "blancas", "negras");
		assertEquals(false, juego.puedeColocarFicha(1, 9));
	}

	@Test(expected = Error.class)
	public void verificarColocarFichaEnFila9Columna9EnTablero8x8() {
		Reversi juego = new Reversi(8, "blancas", "negras");
		assertEquals(false, juego.puedeColocarFicha(9, 9));
	}
	
	@Test ( expected = Error.class )
	public void verificarColocarFichaEnFila0Columna1EnTablero8x8 () {
		Reversi juego = new Reversi(8, "blancas", "negras");
		assertEquals(false, juego.puedeColocarFicha(0, 1));
	}
	
	@Test ( expected = Error.class )
	public void verificarColocarFichaEnFila1Columna0EnTablero8x8 () {
		Reversi juego = new Reversi(8, "blancas", "negras");
		assertEquals(false, juego.puedeColocarFicha(1, 0));
	}
	
	@Test ( expected = Error.class )
	public void verificarColocarFichaEnFila0Columna0EnTablero8x8 () {
		Reversi juego = new Reversi(8, "blancas", "negras");
		assertEquals(false, juego.puedeColocarFicha(0, 0));
	}
	
	@Test(expected = Error.class)
	public void verificarColocarFichaEnFila11Columna1EnTablero10x10() {
		Reversi juego = new Reversi(10, "blancas", "negras");
		assertEquals(false, juego.puedeColocarFicha(11, 1));
	}
	
	@Test(expected = Error.class)
	public void verificarColocarFichaEnFila1Columna11EnTablero10x10() {
		Reversi juego = new Reversi(10, "blancas", "negras");
		assertEquals(false, juego.puedeColocarFicha(1, 11));
	}
	
	@Test(expected = Error.class)
	public void verificarColocarFichaEnFila11Columna11EnTablero10x10() {
		Reversi juego = new Reversi(10, "blancas", "negras");
		assertEquals(false, juego.puedeColocarFicha(11, 11));
	}
	
	@Test ( expected = Error.class )
	public void verificarColocarFichaEnFila0Columna1EnTablero10x10 () {
		Reversi juego = new Reversi(10, "blancas", "negras");
		assertEquals(false, juego.puedeColocarFicha(0, 1));
	}
	
	@Test ( expected = Error.class )
	public void verificarColocarFichaEnFila1Columna0EnTablero10x10 () {
		Reversi juego = new Reversi(10, "blancas", "negras");
		assertEquals(false, juego.puedeColocarFicha(1, 0));
	}
	
	@Test ( expected = Error.class )
	public void verificarColocarFichaEnFila0Columna0EnTablero10x10 () {
		Reversi juego = new Reversi(10, "blancas", "negras");
		assertEquals(false, juego.puedeColocarFicha(0, 0));
	}
	
	@Test
	public void verificarColocarFichaEnFila1Columna1EnTablero4x4() {
		Reversi juego = new Reversi(4, "blancas", "negras");
		assertEquals(false, juego.puedeColocarFicha(1, 1));
	}
	
	@Test
	public void verificarColocarFichaEnFila1Columna3EnTablero4x4() {
		Reversi juego = new Reversi(4, "blancas", "negras");
		assertEquals(false, juego.puedeColocarFicha(1, 2));
	}
	
	@Test
	public void verificarColocarFichaEnFila1Columna4EnTablero4x4() {
		Reversi juego = new Reversi(4, "blancas", "negras");
		assertEquals(false, juego.puedeColocarFicha(1, 4));
	}
	
	@Test
	public void verificarColocarFichaEnFila2Columna1EnTablero4x4() {
		Reversi juego = new Reversi(4, "blancas", "negras");
		assertEquals(false, juego.puedeColocarFicha(2, 1));
	}
	
	@Test
	public void verificarColocarFichaEnFila2Columna2EnTablero4x4() {
		Reversi juego = new Reversi(4, "blancas", "negras");
		assertEquals(false, juego.puedeColocarFicha(2, 2));
	}
	
	@Test
	public void verificarColocarFichaEnFila2Columna3EnTablero4x4() {
		Reversi juego = new Reversi(4, "blancas", "negras");
		assertEquals(false, juego.puedeColocarFicha(2, 3));
	}
	
	@Test
	public void verificarColocarFichaEnFila3Columna2EnTablero4x4() {
		Reversi juego = new Reversi(4, "blancas", "negras");
		assertEquals(false, juego.puedeColocarFicha(3, 2));
	}
	
	@Test
	public void verificarColocarFichaEnFila3Columna3EnTablero4x4() {
		Reversi juego = new Reversi(4, "blancas", "negras");
		assertEquals(false, juego.puedeColocarFicha(3, 3));
	}
	
	@Test
	public void verificarColocarFichaEnFila3Columna4EnTablero4x4() {
		Reversi juego = new Reversi(4, "blancas", "negras");
		assertEquals(false, juego.puedeColocarFicha(3, 4));
	}
	
	@Test
	public void verificarColocarFichaEnFila4Columna1EnTablero4x4() {
		Reversi juego = new Reversi(4, "blancas", "negras");
		assertEquals(false, juego.puedeColocarFicha(4, 1));
	}
	
	@Test
	public void verificarColocarFichaEnFila4Columna3EnTablero4x4() {
		Reversi juego = new Reversi(4, "blancas", "negras");
		assertEquals(false, juego.puedeColocarFicha(4, 3));
	}
	
	@Test
	public void verificarColocarFichaEnFila4Columna4EnTablero4x4() {
		Reversi juego = new Reversi(4, "blancas", "negras");
		assertEquals(false, juego.puedeColocarFicha(4, 4));
	}
	
	// Metodo hayGanador
	
	@Test
	public void verificarEmpate () {
		Reversi juego = new Reversi(4, "blancas", "negras");
		juego.colocarFicha(1, 3);
		juego.colocarFicha(1, 2);
		juego.colocarFicha(1, 1);
		juego.colocarFicha(1, 4);
		juego.colocarFicha(2, 4);
		juego.colocarFicha(3, 4);
		juego.colocarFicha(4, 4);
		juego.colocarFicha(2, 1);
		juego.colocarFicha(3, 1);
		juego.colocarFicha(4, 1);
		juego.colocarFicha(4, 2);
		juego.colocarFicha(4, 3);
		assertEquals( false , juego.hayGanador());
	}
	@Test
	public void verificarEmpate2 () {
		Reversi juego = new Reversi(4, "blancas", "negras");
		juego.colocarFicha(1, 3);
		juego.colocarFicha(3, 4);
		juego.colocarFicha(4, 2);
		juego.colocarFicha(3, 1);
		juego.colocarFicha(4, 3);
		juego.colocarFicha(1, 2);
		juego.colocarFicha(2, 1);
		juego.colocarFicha(1, 1);
		juego.colocarFicha(1, 4);
		juego.colocarFicha(2, 4);
		juego.colocarFicha(4, 4);
		juego.colocarFicha(4, 1);
		assertEquals( false , juego.hayGanador());
	}	
	
	@Test
	public void verificarSiHayGanadorCuandoGananLasFichasBlancas () {
		Reversi juego = new Reversi(4, "blancas", "negras");
		juego.colocarFicha(1, 3);
		juego.colocarFicha(1, 2);
		juego.colocarFicha(3, 1);
		juego.colocarFicha(1, 4);
		//No hubo movimiento posible para cruces
		juego.colocarFicha(3, 4);
		juego.colocarFicha(2, 4);
		juego.colocarFicha(4, 3);
		juego.colocarFicha(4, 4);
		juego.colocarFicha(2, 1);
		juego.colocarFicha(1, 1);
		//No hubo moviemiento posible para circulos
		juego.colocarFicha(4, 2);
		juego.colocarFicha(4, 1);
		assertEquals( true , juego.hayGanador());
	}
	
	@Test
	public void verificarSiHayGanadorCuandoGananLasFichasNegras () {
		Reversi juego = new Reversi(4, "blancas", "negras");
		juego.colocarFicha(1, 3);
		juego.colocarFicha(1, 4);
		juego.colocarFicha(2, 4);
		juego.colocarFicha(3, 4);
		juego.colocarFicha(4, 3);
		juego.colocarFicha(2, 1);
		juego.colocarFicha(3, 1);
		juego.colocarFicha(4, 1);
		juego.colocarFicha(4, 2);
		juego.colocarFicha(1, 2);
		juego.colocarFicha(4, 4);
		juego.colocarFicha(1, 1);
		assertEquals( true , juego.hayGanador());
	}
	
	//Metodo obtener jugador actual
	
	
	@Test
	public void obtenerJugadorCuandoEsTurnoDeLasBlancas () {
		Reversi juego = new Reversi(4, "blancas", "negras");
		assertEquals ( "blancas", juego.obtenerJugadorActual() );
	}
	
	@Test
	public void obtenerJugadorCuandoEsTurnoDeLasNegras () {
		Reversi juego = new Reversi(4, "blancas", "negras");
		juego.colocarFicha(1, 3);
		assertEquals ( "negras", juego.obtenerJugadorActual() );
	}
	
	@Test
	public void obtenerJugadorLuegoDeReiniciar () {
		Reversi juego = new Reversi(4, "blancas", "negras");
		juego.colocarFicha(1, 3);
		juego.reiniciarJuego();
		assertEquals ( "blancas", juego.obtenerJugadorActual() );
	}
	
	public void obtener_fichas(){
		
	}
	@Test
	public void obtenerJuegoTerminadoCuandoNingunJugadorTieneMovimientosPosibles() {
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
	
	

}
