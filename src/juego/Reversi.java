package juego;

/**
 * Juego Reversi
 * <p>
 * Reglas:
 * <p>
 * https://es.wikipedia.org/wiki/Reversi https://es.wikihow.com/jugar-a-Othello
 */
public class Reversi {

	/* Constantes del Tablero */
	private static final int MAXIMO_DEL_TABLERO = 10;
	private static final int MINIMO_DEL_TABLERO = 4;
	private static final int MAXIMO_DE_CARACTERES = 10;
	private static final int MINIMO_DE_CARACTERES = 3;

	private String[] jugadores = new String[2];
	private String jugadorActual = jugadores[0];
	private Casillero tiroActual = Casillero.CRUZ;
	private String ganador = jugadorActual;
	private Casillero tiroOponente = Casillero.CIRCULO;

	private Casillero[][] matrizReversi;
	private Casillero[][] matrizEnglobadora;
	
	private Animacion animaciones;


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

	public Reversi(int dimensionTablero, String fichasNegras,
			String fichasBlancas) {
		validarTablero(dimensionTablero);
		validarJugadores(fichasNegras, fichasBlancas);
		cargarJugadores(fichasNegras, fichasBlancas);
		armarMatrizReversi(dimensionTablero);
		armarMatrizGlobal();
		armarAnimaciones();
	}

	/**
	 * post: devuelve el jugador que tenga el tiro actual.
	 */
	public Casillero obtenerTiroActual() {
		return this.tiroActual;
	}

	/**
	 * post: arma la matriz Reversi pintando en la zona central los casilleros
	 */

	private void armarMatrizReversi(int dimension) {
		this.matrizReversi = new Casillero[dimension][dimension];
		int mitadDelTablero = matrizReversi.length / 2;
		// pintamos la matriz de casilleros vacios
		for (int i = 0; i < this.matrizReversi.length; i++) {
			for (int j = 0; j < this.matrizReversi[i].length; j++) {
				this.matrizReversi[i][j] = Casillero.LIBRE;
			}
		}
		// pintamos los casilleros blancos y negro para cada jugador
		this.matrizReversi[mitadDelTablero][mitadDelTablero] = Casillero.CRUZ;
		this.matrizReversi[mitadDelTablero - 1][mitadDelTablero] = Casillero.CIRCULO;
		this.matrizReversi[mitadDelTablero - 1][mitadDelTablero - 1] = Casillero.CRUZ;
		this.matrizReversi[mitadDelTablero][mitadDelTablero - 1] = Casillero.CIRCULO;

	}

	/**
	 * pre: matriz reversi haya sido inicializada post: arma la matriz global
	 * que encierra a la matriz reversi agregandole un casillero en cada esquina
	 * con valores de NULA
	 */
	private void armarMatrizGlobal() {
		this.matrizEnglobadora = new Casillero[matrizReversi.length + 2][matrizReversi.length + 2];

		for (int i = 0; i < matrizEnglobadora.length; i++) {
			for (int j = 0; j < matrizEnglobadora[i].length; j++) {
				this.matrizEnglobadora[i][j] = Casillero.NULA;
			}
		}
		for (int i = 0; i < matrizReversi.length; i++) {
			for (int j = 0; j < matrizReversi[i].length; j++) {
				this.matrizEnglobadora[i + 1][j + 1] = matrizReversi[i][j];
			}
		}
	}

	/* ----- METODOS DE VALIDACION DE ENTRADA DE DATOS ------ */
	/**
	 * @param fichasNegras
	 *            : nombre del jugador con fichas negras.
	 * @param fichasBlancas
	 *            : nombre del jugador con fichas blancas. post: ejecuta una
	 *            error en caso que los nombres no esten entre el maximo y
	 *            minimo de caracteres permitidos y minimo
	 */

	private void validarJugadores(String fichasNegras, String fichasBlancas) {
		String nombreCruces = fichasBlancas.replaceAll("\\s", "");
		String nombreCirculo = fichasNegras.replaceAll("\\s", "");
		if (nombreCruces.length() == 0 || nombreCirculo.length() == 0) {
			throw new Error("Por favor ingrese un nombre para los jugadores");
		}
		if (nombreCruces.length() > MAXIMO_DE_CARACTERES
				|| nombreCirculo.length() > MAXIMO_DE_CARACTERES
				|| nombreCruces.length() < MINIMO_DE_CARACTERES
				|| nombreCirculo.length() < MINIMO_DE_CARACTERES) {
			throw new Error(
					"El número de caracteres no puede superar los 10 ni ser menor a 3");
		}
	}

	/**
	 * @param dimensionTablero
	 *            : cantidad de filas y columnas que tiene el tablero. post:
	 *            ejecuta una error en caso que el tablero no esten entre el
	 *            maximo y minimo de dimensiones permitidas y si la dimension no
	 *            es par
	 */

	private void validarTablero(int dimensionTablero) {
		if (dimensionTablero < MINIMO_DEL_TABLERO) {
			throw new Error("El tablero debe tener una dimensión minima de 4x4");
		}
		if (dimensionTablero > MAXIMO_DEL_TABLERO) {
			throw new Error(
					"El tablero debe tener una dimensión no mayor a 10x10");
		}
		if (dimensionTablero % 2 != 0) {
			throw new Error("El tablero debe ser de un número par");
		}
	}

	/**
	 * @param fila
	 * @param columna
	 *            post: ejecuta una error en caso que el tablero no esten entre
	 *            los limites del tablero
	 */
	private void validarPosicion(int fila, int columna) {
		if (fila < 1 || columna < 1 || fila > this.matrizReversi.length
				|| columna > this.matrizReversi.length) {
			throw new Error("Posición fuera del tablero");
		}
	}

	/**
	 * @param fila
	 * @param columna
	 *            post: ejecuta una error en caso de no poder colocar ficha en
	 *            el casillero
	 */
	private void validarPuedeColocar(int fila, int columna) {
		if (!puedeColocarFicha(fila, columna)) {
			throw new Error("No se puede colocar ficha en este casillero");
		}
	}

	/* ------ METODOS DE CARGA DEL TABLERO ---------- */
	/**
	 * post: almacena en jugadores, el nombre de cada uno de los jugadores
	 */
	private void cargarJugadores(String fichasNegras, String fichasBlancas) {

		String nombreCruces = fichasBlancas.replaceAll("\\s", "");
		String nombreCirculo = fichasNegras.replaceAll("\\s", "");

		jugadores[0] = nombreCirculo;
		jugadores[1] = nombreCruces;
		this.jugadorActual = jugadores[0];
	}

	/**
	 * post: devuelve la cantidad de filas que tiene el tablero.
	 */
	public int contarFilas() {

		return this.matrizReversi.length;
	}

	/**
	 * post: devuelve la cantidad de columnas que tiene el tablero.
	 */
	public int contarColumnas() {

		return this.matrizReversi.length;
	}

	/**
	 * post: cambia un turno en el juego
	 */
	private void cambiarTurno() {
		switch (tiroActual) {
		case CIRCULO:
			tiroActual = Casillero.CRUZ;
			tiroOponente = Casillero.CIRCULO;
			jugadorActual = jugadores[0];
			break;
		case CRUZ:
			tiroActual = Casillero.CIRCULO;
			tiroOponente = Casillero.CRUZ;
			jugadorActual = jugadores[1];
			break;
		default:
			tiroActual = Casillero.CRUZ;
			tiroOponente = Casillero.CIRCULO;
			jugadorActual = jugadores[0];
			break;
		}
	}

	/**
	 * post: reinicia el juego volviendo al tablero por default y restableciendo
	 * el turno actual
	 */
	public void reiniciarJuego() {
		armarMatrizGlobal();
		tiroActual = Casillero.CRUZ;
		tiroOponente = Casillero.CIRCULO;
		jugadorActual = jugadores[0];
		int mitadDelTablero = matrizEnglobadora.length / 2;
		animaciones.agregarAnimaciones(mitadDelTablero,mitadDelTablero);
		animaciones.agregarAnimaciones(mitadDelTablero-1,mitadDelTablero);
		animaciones.agregarAnimaciones(mitadDelTablero-1,mitadDelTablero-1);
		animaciones.agregarAnimaciones(mitadDelTablero,mitadDelTablero-1);
	}

	/**
	 * pre: recibe indice que esta en el intervalo [1, jugador.lenght] post:
	 * devuelve el nombre del jugador
	 */
	public String obtenerJugadores(int indice) {
		if (indice < 1 || indice > jugadores.length) {
			throw new Error("No existe jugador");
		}
		return this.jugadores[indice - 1];
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
		validarPosicion(fila, columna);
		return this.matrizEnglobadora[fila][columna];
	}

	/**
	 * pre : fila está en el intervalo [1, contarFilas()], columnas está en el
	 * intervalo [1, contarColumnas()]. post: indica quién tiene la posesión del
	 * casillero dado por fila y columna.
	 *
	 * @param fila
	 * @param columna
	 *            post: devuelve si el jugador actual puede colocar una ficha en
	 *            el casillero
	 */
	public boolean puedeColocarFicha(int fila, int columna) {
		validarPosicion(fila, columna);
		boolean sePuedeColocarFicha = false;
		if (matrizEnglobadora[fila][columna] == Casillero.LIBRE) {
			// arriba abajo
			if (matrizEnglobadora[fila + 1][columna] == tiroOponente
					&& !sePuedeColocarFicha) {
				sePuedeColocarFicha = hayFichaEnElMedio(fila, columna, 1, 0);
			}
			if (matrizEnglobadora[fila - 1][columna] == tiroOponente
					&& !sePuedeColocarFicha) {
				sePuedeColocarFicha = hayFichaEnElMedio(fila, columna, -1, 0);
			}
			// izquierda derecha
			if (matrizEnglobadora[fila][columna + 1] == tiroOponente
					&& !sePuedeColocarFicha) {
				sePuedeColocarFicha = hayFichaEnElMedio(fila, columna, 0, 1);
			}
			if (matrizEnglobadora[fila][columna - 1] == tiroOponente
					&& !sePuedeColocarFicha) {
				sePuedeColocarFicha = hayFichaEnElMedio(fila, columna, 0, -1);
			}

			// diagonales
			if (matrizEnglobadora[fila - 1][columna - 1] == tiroOponente
					&& !sePuedeColocarFicha) {
				sePuedeColocarFicha = hayFichaEnElMedio(fila, columna, -1, -1);
			}
			if (matrizEnglobadora[fila + 1][columna + 1] == tiroOponente
					&& !sePuedeColocarFicha) {
				sePuedeColocarFicha = hayFichaEnElMedio(fila, columna, 1, 1);
			}
			if (matrizEnglobadora[fila - 1][columna + 1] == tiroOponente
					&& !sePuedeColocarFicha) {
				sePuedeColocarFicha = hayFichaEnElMedio(fila, columna, -1, 1);
			}
			if (matrizEnglobadora[fila + 1][columna - 1] == tiroOponente
					&& !sePuedeColocarFicha) {
				sePuedeColocarFicha = hayFichaEnElMedio(fila, columna, 1, -1);
			}
		}
		return sePuedeColocarFicha;
	}

	/**
	 * pre : fila está en el intervalo [1, contarFilas()], columnas está en el
	 * intervalo [1, contarColumnas()]
	 *
	 * @param fila
	 * @param columna
	 * @param direccion_fila
	 *            :recibe valores entre 1 y -1, para indicar para que lado
	 *            realiza el analisis, en caso de ser 1 sera para abajo y en
	 *            viceversa para arriba
	 * @param direccion_columna
	 *            :valores entre 1 y -1, para indicar para que lado realiza el
	 *            analisis, en caso de ser 1 sera para la derecha y en viceversa
	 *            para izquierda post: devuelve si hay una ficha del turno
	 *            actual contiguos al casillero
	 */

	private boolean hayFichaEnElMedio(int fila, int columna,
			int direccion_fila, int direccion_columna) {
		boolean hayFichaEnMedio = false;
		boolean noSeFueDelTablero = true;
		while (noSeFueDelTablero && !hayFichaEnMedio) {
			fila += direccion_fila;
			columna += direccion_columna;
			if (matrizEnglobadora[fila][columna] == tiroActual) {
				hayFichaEnMedio = true;
			}
			if (matrizEnglobadora[fila][columna] == Casillero.NULA
					|| matrizEnglobadora[fila][columna] == Casillero.LIBRE) {
				noSeFueDelTablero = false;
			}
		}
		return hayFichaEnMedio;
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

	public void colocarFicha(int fila, int columna) {
		validarPosicion(fila, columna);
		validarPuedeColocar(fila, columna);
		animaciones.reiniciarAnimaciones();
		if (matrizEnglobadora[fila][columna] == Casillero.LIBRE) {
			if (matrizEnglobadora[fila + 1][columna] == tiroOponente) {
				pintarCasilleros(fila, columna, 1, 0);
			}
			if (matrizEnglobadora[fila - 1][columna] == tiroOponente) {
				pintarCasilleros(fila, columna, -1, 0);
			}
			if (matrizEnglobadora[fila][columna + 1] == tiroOponente) {
				pintarCasilleros(fila, columna, 0, 1);
			}
			if (matrizEnglobadora[fila][columna - 1] == tiroOponente) {
				pintarCasilleros(fila, columna, 0, -1);
			}
			if (matrizEnglobadora[fila - 1][columna - 1] == tiroOponente) {
				pintarCasilleros(fila, columna, -1, -1);
			}
			if (matrizEnglobadora[fila + 1][columna + 1] == tiroOponente) {
				pintarCasilleros(fila, columna, 1, 1);
			}
			if (matrizEnglobadora[fila - 1][columna + 1] == tiroOponente) {
				pintarCasilleros(fila, columna, -1, 1);
			}
			if (matrizEnglobadora[fila + 1][columna - 1] == tiroOponente) {
				pintarCasilleros(fila, columna, 1, -1);
			}
			this.matrizEnglobadora[fila][columna] = tiroActual;
			cambiarTurno();
		} else {
			throw new Error("El casillero esta ocupado");
		}
	}

	/**
	 * pre : fila está en el intervalo [1, contarFilas()], columnas está en el
	 * intervalo [1, contarColumnas()]
	 *
	 * @param fila
	 * @param columna
	 * @param direccion_fila
	 *            :recibe valores entre 1 y -1, para indicar para que lado
	 *            pintar casilleros, en caso de ser 1 sera para abajo y en
	 *            viceversa para arriba
	 * @param direccion_columna
	 *            :valores entre 1 y -1, para indicar para que lado pintar
	 *            casilleros, en caso de ser 1 sera para la derecha y en
	 *            viceversa para izquierda post: coloca ficha del turno actual
	 *            en los casilleros que sean del tiro oponente hasta toparse con
	 *            un tiroActual o llegue a los limites del tablero
	 */

	private void pintarCasilleros(int fila, int columna, int direccion_fila,
			int direccion_columna) {
		if (hayFichaEnElMedio(fila, columna, direccion_fila, direccion_columna)) {
			while (matrizEnglobadora[fila][columna] != Casillero.NULA
					&& !(matrizEnglobadora[fila][columna] == tiroActual)) {
				if (matrizEnglobadora[fila][columna] == tiroOponente) {
					matrizEnglobadora[fila][columna] = tiroActual;
					animaciones.agregarAnimaciones(fila, columna);
				}
				fila += direccion_fila;
				columna += direccion_columna;
			}
		}

	}

	/**
	 * post: devuelve la cantidad de fichas negras en el tablero.
	 */
	public int contarFichasNegras() {
		int fichasNegras = 0;
		for (int i = 0; i < this.matrizReversi.length; i++) {
			for (int j = 0; j < this.matrizReversi[i].length; j++) {
				if (this.matrizEnglobadora[i + 1][j + 1] == Casillero.CRUZ) {
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
		for (int i = 0; i < this.matrizReversi.length; i++) {
			for (int j = 0; j < this.matrizReversi[i].length; j++) {
				if (this.matrizEnglobadora[i + 1][j + 1] == Casillero.CIRCULO) {
					fichasBlancas++;
				}
			}
		}
		return fichasBlancas;
	}

	/**
	 * post: devuelve la cantidad de fichas ocupadas en el tablero.
	 */

	public int contarFichasOcupadas() {
		int fichasOcupadas = 0;
		for (int i = 0; i < this.matrizReversi.length; i++) {
			for (int j = 0; j < this.matrizReversi[i].length; j++) {
				if (this.matrizEnglobadora[i + 1][j + 1] == Casillero.CRUZ
						|| this.matrizEnglobadora[i + 1][j + 1] == Casillero.CIRCULO) {
					fichasOcupadas++;
				}
			}
		}
		return fichasOcupadas;
	}

	/**
	 * post: devuelve la cantidad de movimientos posibles en el tablero.
	 */

	public int contarMovimientosPosibles() {
		int movimientosPosibles = 0;
		for (int i = 0; i < this.matrizReversi.length; i++) {
			for (int j = 0; j < this.matrizReversi[i].length; j++) {
				if (puedeColocarFicha(i + 1, j + 1)) {
					movimientosPosibles++;
				}
			}
		}
		return movimientosPosibles;
	}

	/**
	 * post: indica si el juego terminó porque no existen casilleros vacíos o
	 * ninguno de los jugadores puede colocar una ficha.
	 */

	public boolean termino() {
		if (contarMovimientosPosibles() == 0) {
			cambiarTurno();
		}
		return (contarFichasOcupadas() == (this.matrizReversi.length * this.matrizReversi.length)) || (contarMovimientosPosibles() == 0);
	}

	/**
	 * post: indica si el juego terminó y tiene un ganador.
	 */
	public boolean hayGanador() {
		boolean huboGanador = false;
		if (contarFichasNegras() > contarFichasBlancas()) {
			ganador = jugadores[0];
			huboGanador = true;
		} else if (contarFichasNegras() < contarFichasBlancas()) {
			ganador = jugadores[1];
			huboGanador = true;
		}
		return huboGanador;
	}

	/**
	 * pre : el juego terminó. post: devuelve el nombre del jugador que ganó el
	 * juego.
	 */
	public String obtenerGanador() {
		return ganador;
	}

	/**
	 * post: crea el arreglo donde se almacenan el estado de las animaciones de
	 * los casilleros
	 */

	private void armarAnimaciones() {
		animaciones = new Animacion(matrizEnglobadora.length);
	}

	/**
	 * post: restablece el estado de las animaciones
	 */



	/**
	 * @param fila
	 * @param columna
	 *            post: devuelve el estado de animacion del casillero
	 */
	public int obtenerAnimaciones(int fila, int columna) {
		return animaciones.obtenerAnimaciones(fila, columna);
	}

}
