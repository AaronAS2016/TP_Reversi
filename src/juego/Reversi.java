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

    private String[] jugadores = new String[2];
    private String jugadorActual = jugadores[0];
    private Casillero tiroActual = Casillero.NEGRAS;
    private String ganador = jugadorActual;
    private Casillero tiroOponente = Casillero.BLANCAS;

    private Casillero[][] matrizReversi;
    private Casillero[][] matrizEnglobadora;


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
        validarTablero(dimensionTablero);
        validarJugadores(fichasNegras, fichasBlancas);
        cargarJugadores(fichasNegras, fichasBlancas);
        armarMatrizGlobalReversi(dimensionTablero);
        armarMatrizGlobal(this.matrizReversi);
    }

    private void armarMatrizGlobal(Casillero[][] matriz) {
        this.matrizEnglobadora = new Casillero[matriz.length + 2][matriz.length + 2];
        //INICIALIZAMOS EN NULA
        for (int i = 0; i < matrizEnglobadora.length; i++) {
            for (int j = 0; j < matrizEnglobadora[i].length; j++) {
                this.matrizEnglobadora[i][j] = Casillero.NULA;
            }
        }
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                this.matrizEnglobadora[i + 1][j + 1] = matriz[i][j];
            }
        }
    }

    private void armarMatrizGlobalReversi(int dimension) {
        this.matrizReversi = new Casillero[dimension][dimension];
        int mitadDelTablero = matrizReversi.length / 2;
        // pintamos la matriz de casilleros vacios
        for (int i = 0; i < this.matrizReversi.length; i++) {
            for (int j = 0; j < this.matrizReversi[i].length; j++) {
                this.matrizReversi[i][j] = Casillero.LIBRE;
            }
        }
        // pintamos los casilleros blancos y negro para cada jugador
        this.matrizReversi[mitadDelTablero][mitadDelTablero] = Casillero.NEGRAS;
        this.matrizReversi[mitadDelTablero - 1][mitadDelTablero] = Casillero.BLANCAS;
        this.matrizReversi[mitadDelTablero - 1][mitadDelTablero - 1] = Casillero.NEGRAS;
        this.matrizReversi[mitadDelTablero][mitadDelTablero - 1] = Casillero.BLANCAS;
    }

    /* ----- METODOS DE VALIDACION DE ENTRADA DE DATOS ------ */

    private void validarJugadores(String fichasNegras, String fichasBlancas) {
        if (fichasNegras.length() < 1 || fichasBlancas.length() < 1) {
            throw new Error("Por favor ingrese un nombre para los jugadores");
        }
    }

    private void validarTablero(int dimensionTablero) {
        if (dimensionTablero < MINIMO_DEL_TABLERO || dimensionTablero > MAXIMO_DEL_TABLERO) {
            throw new Error("El tablero debe tener una dimensión minima de 4x4");
        }
        if (dimensionTablero % 2 != 0) {
            throw new Error("El tablero debe ser de un número par");
        }
    }

    /* ------ METODOS DE CARGA DEL TABLERO ---------- */

    private void cargarJugadores(String fichasNegras, String fichasBlancas) {
        jugadores[0] = fichasNegras;
        jugadores[1] = fichasBlancas;
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
     * post: devuelve el nombre del jugador que debe colocar una ficha o null si
     * terminó el juego.
     */
    private void cambiarTurno() {
        switch (tiroActual) {
            case BLANCAS:
                tiroActual = Casillero.NEGRAS;
                tiroOponente = Casillero.BLANCAS;
                jugadorActual = jugadores[0];
                break;
            case NEGRAS:
                tiroActual = Casillero.BLANCAS;
                tiroOponente = Casillero.NEGRAS;
                jugadorActual = jugadores[1];
                break;
            default:
                tiroActual = Casillero.NEGRAS;
                tiroOponente = Casillero.BLANCAS;
                jugadorActual = jugadores[0];
        }

    }

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
        return this.matrizEnglobadora[fila][columna];
    }

    public boolean puedeColocarFicha(int fila, int columna) {
        boolean sePuedeColocarFicha = false;
        if (matrizEnglobadora[fila][columna] == Casillero.LIBRE) {
            if (matrizEnglobadora[fila + 1][columna + 1] == tiroOponente) {
                sePuedeColocarFicha = analizarTablero(fila, columna, 1 , 1);
            }
            if (matrizEnglobadora[fila + 1][columna] == tiroOponente) {
                sePuedeColocarFicha = analizarTablero(fila, columna, 1 , 0);
            }
            if (matrizEnglobadora[fila][columna + 1] == tiroOponente) {
                sePuedeColocarFicha = analizarTablero(fila, columna, 0 , 1);
            }
            if (matrizEnglobadora[fila][columna - 1] == tiroOponente) {
                sePuedeColocarFicha = analizarTablero(fila, columna, 0, -1);
            }
            if (matrizEnglobadora[fila - 1][columna - 1] == tiroOponente) {
                sePuedeColocarFicha = analizarTablero(fila, columna, -1 , -1);
            }
            if (matrizEnglobadora[fila - 1][columna] == tiroOponente) {
                sePuedeColocarFicha = analizarTablero(fila, columna, -1 , 0);
            }
            if (matrizEnglobadora[fila - 1][columna + 1] == tiroOponente) {
                sePuedeColocarFicha = analizarTablero(fila, columna, -1, +1);
            }
            if (matrizEnglobadora[fila + 1][columna - 1] == tiroOponente) {
                sePuedeColocarFicha = analizarTablero(fila, columna, +1, -1);
            }
        }
        return sePuedeColocarFicha;
    }

    private boolean analizarTablero(int fila, int columna, int direccion_fila, int direccion_columna){
        boolean hayFichaEnMedio = false;
        boolean noSeFueDelTablero = true;
        while (noSeFueDelTablero && !hayFichaEnMedio){
            fila+=direccion_fila;
            columna+=direccion_columna;
            if(matrizEnglobadora[fila][columna] == tiroActual){
                hayFichaEnMedio = true;
            }
            if(matrizEnglobadora[fila][columna] == Casillero.NULA){
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
        //vemos izquierda derecha
        colocarFichas(fila, columna, 0, -1);
        colocarFichas(fila, columna, 0, 1);
        //vemos arriba y abajo
        colocarFichas(fila, columna, 1,0);
        colocarFichas(fila, columna, -1, 0);
        //revisamos las esquinas
        colocarFichas(fila, columna,  1,1);
        colocarFichas(fila, columna, -1,1);
        colocarFichas(fila, columna, -1,-1);
        this.matrizEnglobadora[fila][columna] = tiroActual;
        cambiarTurno();
    }

    public void colocarFichas(int fila, int columna, int direccion_fila, int direccion_columna){
        while(matrizEnglobadora[fila][columna] != Casillero.NULA){
            if(matrizEnglobadora[fila][columna] != tiroActual && matrizEnglobadora[fila][columna] != Casillero.LIBRE && matrizEnglobadora[fila][columna] == tiroOponente && analizarTablero(fila,columna,direccion_fila,direccion_columna)){
                matrizEnglobadora[fila][columna] = tiroActual;
            }
            fila+=direccion_fila;
            columna+=direccion_columna;
        }
    }


    /**
     * post: devuelve la cantidad de fichas negras en el tablero.
     */
    public int contarFichasNegras() {
        int fichasNegras = 0;
        for (int i = 0; i < this.matrizReversi.length; i++) {
            for (int j = 0; j < this.matrizReversi[i].length; j++) {
                if (this.matrizEnglobadora[i + 1][j + 1] == Casillero.NEGRAS) {
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
                if (this.matrizEnglobadora[i + 1][j + 1] == Casillero.BLANCAS) {
                    fichasBlancas++;
                }
            }
        }
        return fichasBlancas;
    }

    public int contarFichasOcupadas(){
        int fichasOcupadas = 0;
        for (int i = 0; i < this.matrizReversi.length; i++) {
            for (int j = 0; j < this.matrizReversi[i].length; j++) {
                if (this.matrizEnglobadora[i + 1][j + 1] != Casillero.LIBRE ) {
                    fichasOcupadas++;
                }
            }
        }
        return  fichasOcupadas;
    }

    public int contarMovimientosPosibles(){
        int movimientosPosibles = 0;
        for (int i = 0; i < this.matrizReversi.length; i++) {
            for (int j = 0; j < this.matrizReversi[i].length; j++) {
                if (puedeColocarFicha(i+1,j+1)) {
                    movimientosPosibles++;
                }
            }
        }
        return  movimientosPosibles;
    }

    /**
     * post: indica si el juego terminó porque no existen casilleros vacíos o
     * ninguno de los jugadores puede colocar una ficha.
     */

    public boolean termino() {
        boolean termino = false;
        if((contarFichasOcupadas() == (this.matrizReversi.length * this.matrizReversi.length))) {
            termino = true;
        }

        if(contarMovimientosPosibles() == 0){
            termino = true;
        }


        return termino;
    }

    /**
     * post: indica si el juego terminó y tiene un ganador.
     */
    public boolean hayGanador() {
        boolean huboEmpate = true;
        if(contarFichasNegras() > contarFichasBlancas()){
            ganador = jugadores[0];
            huboEmpate = false;
        }else if (contarFichasNegras() < contarFichasBlancas()){
            ganador = jugadores[1];
            huboEmpate = false;
        }else{
            huboEmpate = true;
        }
        return huboEmpate;
    }

    /**
     * pre : el juego terminó. post: devuelve el nombre del jugador que ganó el
     * juego.
     */
    public String obtenerGanador() {
        return ganador;
    }

}
