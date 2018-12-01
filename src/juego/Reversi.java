package juego;

/**
 * Juego Reversi
 * <p>
 * Reglas:
 * <p>
 * https://es.wikipedia.org/wiki/Reversi https://es.wikihow.com/jugar-a-Othello
 */
public class Reversi {


    private String[] jugadores = new String[2];
    private String jugadorActual = jugadores[0];
    private Casillero tiroActual = Casillero.CRUZ;
    private String ganador = jugadorActual;
    private Casillero tiroOponente = Casillero.CIRCULO;

    private Casillero[][] matrizReversi;

    private Animacion animaciones;
    private Examinador examinador;
    private Pincel pincel;
    private Validador validador;


    /**
     * pre : 'dimension' es un número par, mayor o igual a 4. post: empieza el
     * juego entre el jugador que tiene fichas negras, identificado como
     * 'fichasNegras' y el jugador que tiene fichas blancas, identificado como
     * 'fichasBlancas'. El tablero tiene 4 fichas: 2 negras y 2 blancas. Estas
     * fichas están intercaladas en el centro del tablero.
     *
     * @param dimensionTablero : cantidad de filas y columnas que tiene el tablero.
     * @param fichasNegras     : nombre del jugador con fichas negras.
     * @param fichasBlancas    : nombre del jugador con fichas blancas.
     */

    public Reversi(int dimensionTablero, String fichasNegras,
                   String fichasBlancas) {
        cargarJugadores(fichasNegras, fichasBlancas);
        armarMatrizReversi(dimensionTablero);
        armarAnimaciones();
        examinador = new Examinador(matrizReversi);
        pincel = new Pincel(examinador, animaciones, matrizReversi);
        validador = new Validador(this);
        validador.validarTablero(dimensionTablero);
        validador.validarJugadores(fichasNegras, fichasBlancas);
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
        tiroActual = Casillero.CRUZ;
        tiroOponente = Casillero.CIRCULO;
        jugadorActual = jugadores[0];
        armarMatrizReversi(this.contarFilas());
        int mitadDelTablero = matrizReversi.length / 2;
        animaciones.agregarAnimaciones(mitadDelTablero, mitadDelTablero);
        animaciones.agregarAnimaciones(mitadDelTablero - 1, mitadDelTablero);
        animaciones.agregarAnimaciones(mitadDelTablero - 1, mitadDelTablero - 1);
        animaciones.agregarAnimaciones(mitadDelTablero, mitadDelTablero - 1);
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
        validador.validarPosicion(fila, columna, matrizReversi);
        return this.matrizReversi[fila][columna];
    }

    /**
     * pre : fila está en el intervalo [1, contarFilas()], columnas está en el
     * intervalo [1, contarColumnas()]. post: indica quién tiene la posesión del
     * casillero dado por fila y columna.
     *
     * @param fila
     * @param columna post: devuelve si el jugador actual puede colocar una ficha en
     *                el casillero
     */
    public boolean puedeColocarFicha(int fila, int columna) {
        validador.validarPosicion(fila, columna, matrizReversi);

        boolean sePuedeColocarFicha = false;
        if (matrizReversi[fila][columna] == Casillero.LIBRE) {
            sePuedeColocarFicha = examinador.examinarTablero(fila, columna, tiroOponente, tiroActual, pincel);
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

    public void colocarFicha(int fila, int columna) {

        validador.validarPosicion(fila, columna, matrizReversi);
        validador.validarPuedeColocar(fila, columna);
        animaciones.reiniciarAnimaciones();


        if (matrizReversi[fila][columna] == Casillero.LIBRE) {
            if (fila + 1 < matrizReversi.length) {
                if (matrizReversi[fila + 1][columna] == tiroOponente) {
                    pincel.pintarCasilleros(fila, columna, 1, 0, tiroActual, tiroOponente);
                }
            }
            if (fila - 1 > 0) {
                if (matrizReversi[fila - 1][columna] == tiroOponente) {
                    pincel.pintarCasilleros(fila, columna, -1, 0, tiroActual, tiroOponente);
                }
            }
            if (columna + 1 < matrizReversi.length) {
                if (matrizReversi[fila][columna + 1] == tiroOponente) {
                    pincel.pintarCasilleros(fila, columna, 0, 1, tiroActual, tiroOponente);
                }
            }
            if (columna - 1 > 0) {
                if (matrizReversi[fila][columna - 1] == tiroOponente) {
                    pincel.pintarCasilleros(fila, columna, 0, -1, tiroActual, tiroOponente);
                }
            }

            if (fila - 1 > 0 && columna - 1 > 0) {
                if (matrizReversi[fila - 1][columna - 1] == tiroOponente) {
                    pincel.pintarCasilleros(fila, columna, -1, -1, tiroActual, tiroOponente);
                }
            }

            if (fila + 1 < matrizReversi.length && columna + 1 < matrizReversi.length) {
                if (matrizReversi[fila + 1][columna + 1] == tiroOponente) {
                    pincel.pintarCasilleros(fila, columna, 1, 1, tiroActual, tiroOponente);
                }
            }

            if (fila - 1 > 0 && columna + 1 < matrizReversi.length) {
                if (matrizReversi[fila - 1][columna + 1] == tiroOponente) {
                     pincel.pintarCasilleros(fila, columna, -1, 1, tiroActual, tiroOponente);
                }
            }

            if (fila + 1 < matrizReversi.length && columna - 1 > 0) {
                if (matrizReversi[fila + 1][columna - 1] == tiroOponente) {
                    pincel.pintarCasilleros(fila, columna, 1, -1, tiroActual, tiroOponente);
                }
            }

            this.matrizReversi[fila][columna] = tiroActual;
            animaciones.agregarAnimaciones(fila,columna);

            cambiarTurno();

        } else {
            throw new Error("El casillero esta ocupado");
        }
    }


    /**
     * post: devuelve la cantidad de fichas negras en el tablero.
     */
    public int contarFichasNegras() {
        return contar(Casillero.CRUZ);
    }

    private int contar(Casillero casilleroABuscar) {
        int cantidadDeFichas = 0;
        for (int i = 0; i < this.matrizReversi.length; i++) {
            for (int j = 0; j < this.matrizReversi[i].length; j++) {
                if (this.matrizReversi[i][j] == casilleroABuscar) {
                    cantidadDeFichas++;
                }
            }
        }
        return cantidadDeFichas;
    }

    /**
     * post: devuelve la cantidad de fichas blancas en el tablero.
     */
    public int contarFichasBlancas() {
        return contar(Casillero.CIRCULO);
    }

    /**
     * post: devuelve la cantidad de fichas ocupadas en el tablero.
     */

    public int contarFichasOcupadas() {
        int fichasOcupadas = 0;
        for (int i = 0; i < this.matrizReversi.length; i++) {
            for (int j = 0; j < this.matrizReversi[i].length; j++) {
                if (this.matrizReversi[i][j] == Casillero.CRUZ
                        || this.matrizReversi[i][j] == Casillero.CIRCULO) {
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
                if (puedeColocarFicha(i , j)) {
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
        if (contarFichasNegras() > contarFichasBlancas()) {
            ganador = jugadores[0];
        } else if (contarFichasNegras() < contarFichasBlancas()) {
            ganador = jugadores[1];
        }
        return (contarFichasNegras() > contarFichasBlancas()) || contarFichasBlancas() > contarFichasNegras();
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
        animaciones = new Animacion(matrizReversi.length);
    }

    /**
     * @param fila
     * @param columna post: devuelve el estado de animacion del casillero
     */
    public int obtenerAnimaciones(int fila, int columna) {
        return animaciones.obtenerAnimaciones(fila, columna);
    }

}
