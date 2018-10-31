package juego;

/**
 * Juego Reversi
 * 
 * Reglas:
 * 
 * https://es.wikipedia.org/wiki/Reversi https://es.wikihow.com/jugar-a-Othello
 * 
 */
public class Reversi {
	
	
	
	private int filas;
	private int columnas;
	private String[] jugadores = new String[2];
	private String jugadorActual;
	private Casillero casilleroActual = Casillero.NEGRAS;
	
	private Casillero[][] matrizReversi;

	/**
	 * pre : 'dimension' es un número par, mayor o igual a 4. post: empieza el
	 * juego entre el jugador que tiene fichas negras, identificado como
	 * 'fichasNegras' y el jugador que tiene fichas blancas, identificado como
	 * 'fichasBlancas'. El tablero tiene 4 fichas: 2 negras y 2 blancas. Estas
	 * fichas están intercaladas en el centro del tablero.
	 * 
	 * @param dimensionTablero
	 *            : cantidad de filas y columnas que tiene el tablero.
	 * @param fichasNegras
	 *            : nombre del jugador con fichas negras.
	 * @param fichasBlancas
	 *            : nombre del jugador con fichas blancas.
	 */


	public Reversi(int dimensionTablero, String fichasNegras, String fichasBlancas) {
		validarTablero(dimensionTablero);
		validarJugadores(fichasNegras, fichasBlancas);
		cargarTablero(dimensionTablero);
		cargarJugadores(fichasNegras, fichasBlancas);
		armarTablero(dimensionTablero);
	}
	
	private void armarTablero(int dimension){
		this.matrizReversi = new Casillero[dimension][dimension];
		int mitadDelTablero = matrizReversi.length / 2;
		//pintamos la matriz de casilleros vacios
		for(int i = 0; i < this.matrizReversi.length; i++){
			for(int j = 0; j < this.matrizReversi[i].length; j++){
					this.matrizReversi[i][j] = Casillero.LIBRE;
			}
		}
		//pintamos los casilleros blancos y negro para cada jugador
		this.matrizReversi[mitadDelTablero][mitadDelTablero] = Casillero.NEGRAS;
		this.matrizReversi[mitadDelTablero-1][mitadDelTablero] = Casillero.BLANCAS;
		this.matrizReversi[mitadDelTablero-1][mitadDelTablero-1] = Casillero.NEGRAS;
		this.matrizReversi[mitadDelTablero][mitadDelTablero-1] = Casillero.BLANCAS;
	}

	/* ----- METODOS DE VALIDACION DE ENTRADA DE DATOS ------ */

	private void validarJugadores(String fichasNegras, String fichasBlancas) {
		if (fichasNegras.length() < 1 || fichasBlancas.length() < 1) {
			throw new Error("Por favor ingrese un nombre para los jugadores");
		}
	}

	private void validarTablero(int dimensionTablero) {
		if (dimensionTablero < 4) {
			throw new Error("El tablero debe tener una dimensión minima de 4x4");
		}
		if(dimensionTablero %2 != 0){
			throw new Error("El tablero debe ser de un número par");
		}
	}

	/* ------ METODOS DE CARGA DEL TABLERO ---------- */
	private void cargarTablero(int dimension) {
		this.filas = dimension;
		this.columnas = dimension;
	}

	private void cargarJugadores(String fichasNegras, String fichasBlancas) {
		jugadores[0] = fichasNegras;
		jugadores[1] = fichasBlancas;
		this.jugadorActual = jugadores[0];
	}

	/**
	 * post: devuelve la cantidad de filas que tiene el tablero.
	 */
	public int contarFilas() {

		return this.filas;
	}

	/**
	 * post: devuelve la cantidad de columnas que tiene el tablero.
	 */
	public int contarColumnas() {

		return this.columnas;
	}

	/**
	 * post: devuelve el nombre del jugador que debe colocar una ficha o null si
	 * terminó el juego.
	 */
	public String obtenerJugadorActual() {

		return jugadorActual;
	}

	/**
	 * pre : fila está en el intervalo [1, contarFilas()], columnas está en el
	 * intervalo [1, contarColumnas()]. post: indica quién tiene la posesión del
	 * casillero dado por fila y columna.
	 * 
	 * @param fila
	 * @param columna
	 */
	public Casillero obtenerCasillero(int fila, int columna) {
		return this.matrizReversi[fila-1][columna-1];
	}

	public boolean puedeColocarFicha(int fila, int columna) {
		boolean sePuedeColocarFicha = false;
		if(this.matrizReversi[fila-1][columna-1] == Casillero.LIBRE){
			if((!(fila-1 < 0) && !(fila-1 > this.matrizReversi.length))
					&& ((!(columna-1 < 0) && !(columna-1 > this.matrizReversi[0].length)))){
					
					//Vemos si hay posibilidad en la columna
					

					for(int i = 0; i < this.matrizReversi.length;i++){
						if(((fila-1) == 0 && ((columna-1)-i) == 0) {
							System.out.println("ENTRA");
							System.out.println("Fila:" + fila + "\nColumna: " + columna);
							sePuedeColocarFicha = true;
						}
					}
				}
		}
			
		
		return sePuedeColocarFicha;
	}

	/**
	 * pre : la posición indicada por (fila, columna) puede ser ocupada por una
	 * ficha. 'fila' está en el intervalo [1, contarFilas()]. 'columna' está en
	 * el intervalor [1, contarColumnas()]. y aún queda un Casillero.VACIO en la
	 * columna indicada. post: coloca una ficha en la posición indicada.
	 * 
	 * @param fila
	 * @param columna
	 */
	
	//ojo, llegan al revés
	public void colocarFicha(int fila, int columna) {
		//System.out.println("Fila:" + fila + "\nColumna: " + columna);
		switch(casilleroActual){
			case NEGRAS:
				casilleroActual = Casillero.BLANCAS;
				break;
			case BLANCAS:
				casilleroActual = Casillero.NEGRAS;
				break;
			default:
				casilleroActual = Casillero.NEGRAS;
				break;		
		}
		this.matrizReversi[fila-1][columna-1] = casilleroActual;

		
	}

	/**
	 * post: devuelve la cantidad de fichas negras en el tablero.
	 */
	public int contarFichasNegras() {
		int fichasNegras = 0;
		for(int i = 0; i < this.matrizReversi.length; i++){
			for(int j = 0; j < this.matrizReversi[i].length; j++){
				if(this.matrizReversi[i][j] == Casillero.NEGRAS){
					fichasNegras++;
				}		
			}
		}
		return fichasNegras;
	}

	/**
	 * post: devuelve la cantidad de fichas blancas en el tablero.
	 */
	public int contarFichasBlancas() {

		int fichasBlancas = 0;
		for(int i = 0; i < this.matrizReversi.length; i++){
			for(int j = 0; j < this.matrizReversi[i].length; j++){
				if(this.matrizReversi[i][j] == Casillero.BLANCAS){
					fichasBlancas++;
				}		
			}
		}
		return fichasBlancas;
	}

	/**
	 * post: indica si el juego terminó porque no existen casilleros vacíos o
	 * ninguno de los jugadores puede colocar una ficha.
	 */
	public boolean termino() {
		int fichasOcupadas = 0;
		for(int i = 0; i < this.matrizReversi.length; i++){
			for(int j = 0; j < this.matrizReversi[i].length; j++){
				if(this.matrizReversi[i][j] != Casillero.LIBRE){
					fichasOcupadas++;
				}		
			}
		}
		return (fichasOcupadas == (this.matrizReversi.length * this.matrizReversi.length));
	}

	/**
	 * post: indica si el juego terminó y tiene un ganador.
	 */
	public boolean hayGanador() {

		return false;
	}

	/**
	 * pre : el juego terminó. post: devuelve el nombre del jugador que ganó el
	 * juego.
	 */
	public String obtenerGanador() {

		return null;
	}
}
