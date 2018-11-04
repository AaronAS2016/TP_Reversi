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
    private Casillero tiroActual = Casillero.BLANCAS;
    private Casillero tiroOponente = Casillero.NEGRAS;

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
    public Casillero obtenerCasillero(int columna, int fila) {
        return this.matrizEnglobadora[fila][columna];
    }

    public boolean puedeColocarFicha(int columna, int fila) {
        boolean sePuedeColocarFicha = false;
        if (this.matrizEnglobadora[fila][columna] == Casillero.LIBRE) {
            if (this.matrizEnglobadora[fila][columna + 1] == tiroOponente || this.matrizEnglobadora[fila][columna - 1] == tiroOponente) {
                sePuedeColocarFicha = analizarTableroVertical(fila, columna);
            }

            if (this.matrizEnglobadora[fila + 1][columna] == tiroOponente || this.matrizEnglobadora[fila - 1][columna] == tiroOponente) {
                sePuedeColocarFicha = analizarTableroHorizontal(fila, columna);
            }

            if (this.matrizEnglobadora[fila + 1][columna + 1] == tiroOponente || this.matrizEnglobadora[fila - 1][columna - 1] == tiroOponente) {
                sePuedeColocarFicha = analizarTableroDiagonal(fila, columna);
            }
        }

        return sePuedeColocarFicha;
    }

    //ANALISIS

    private boolean analizarTableroDiagonal(int fila, int columna) {
        boolean sePuedeColocarFicha = false;
        for (int i = 1;this.matrizEnglobadora[fila+i][columna + i] != Casillero.NULA; i++) {
            if (this.matrizEnglobadora[fila+ i][columna + i] == tiroActual) {
                sePuedeColocarFicha = true;
            }
        }

        for (int i = 1; this.matrizEnglobadora[fila-i][columna - i] != Casillero.NULA; i++) {
            if (this.matrizEnglobadora[fila- i][columna - i] == tiroActual) {
                sePuedeColocarFicha = true;
            }
        }
        return sePuedeColocarFicha;
    }

    private boolean analizarTableroHorizontal(int fila, int columna) {
        boolean sePuedeColocarFicha = false;
        for (int i = 1; this.matrizEnglobadora[fila+i][columna ] != Casillero.NULA; i++) {
            if (this.matrizEnglobadora[fila+i][columna] == tiroActual) {
                sePuedeColocarFicha = true;
            }
        }

        for (int i = 1; this.matrizEnglobadora[fila-i][columna] != Casillero.NULA; i++) {
            if (this.matrizEnglobadora[fila-i][columna] == tiroActual) {
                sePuedeColocarFicha = true;
            }
        }
        return sePuedeColocarFicha;
    }


    private boolean analizarTableroVertical(int fila, int columna) {
        boolean sePuedeColocarFicha = false;
        for (int i = 1; this.matrizEnglobadora[fila][columna+i] != Casillero.NULA; i++) {
            if (this.matrizEnglobadora[fila][columna+i] == tiroActual) {
                sePuedeColocarFicha = true;
            }
        }

        for (int i = 1; this.matrizEnglobadora[fila][columna -i] != Casillero.NULA; i++) {
            if (this.matrizEnglobadora[fila ][columna-i] == tiroActual) {
                sePuedeColocarFicha = true;
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

    public void colocarFicha(int columna, int fila) {

        //DERECHA IZQUIERDA
        if(puedeColocarFicha(columna, fila)){
            if (this.matrizEnglobadora[fila][columna + 1] == tiroOponente) {
                comprobarDerechaPintarCasillero(fila, columna+1);
            }
        }

        if(puedeColocarFicha(columna, fila)){

            if (this.matrizEnglobadora[fila][columna - 1] == tiroOponente) {
                comprobarIzquierdaPintarCasillero(fila, columna-1);
            }
        }


        if(puedeColocarFicha(columna, fila)){

            //ARRIBA ABAJO

            if (this.matrizEnglobadora[fila - 1][columna] == tiroOponente) {
                comprobarArribaPintarCasillero(fila-1, columna);
            }

        }


        if(puedeColocarFicha(columna, fila)){


            if (this.matrizEnglobadora[fila + 1][columna] == tiroOponente) {
                comprobarAbajoPintarCasillero(fila+1, columna);
            }

        }


        if(puedeColocarFicha(columna, fila)){


            if (this.matrizEnglobadora[fila + 1][columna + 1] == tiroOponente) {
                comprobarAbajoDerechaPintarCasillero(fila+1, columna+1);
            }

        }


        if(puedeColocarFicha(columna, fila)){

            if (this.matrizEnglobadora[fila - 1][columna - 1] == tiroOponente) {
                comprobarArribaIzquierdaPintarCasillero(fila-1, columna-1);
            }

        }


        if(puedeColocarFicha(columna, fila)){

            if (this.matrizEnglobadora[fila + 1][columna - 1] == tiroOponente) {
                comprobarAbajoIzquierdaPintarCasillero(fila+1, columna-1);
            }


        }


        if(puedeColocarFicha(columna, fila)){

            if (this.matrizEnglobadora[fila - 1][columna + 1] == tiroOponente) {
                comprobarArribaDerechaPintarCasillero(fila-1, columna-1);
            }


        }






        /* DIAGONALES */

        //pintamos el casillo libre con la ficha
        this.matrizEnglobadora[fila][columna] = tiroActual;
        cambiarTurno();
    }

    //DIAGONALES
    private void comprobarArribaIzquierdaPintarCasillero(int fila, int columna) {
        int casilleroADetenerse = 0;
        for (int i = 0; this.matrizEnglobadora[fila - i][columna - i] != tiroActual && this.matrizEnglobadora[fila - i][columna - i] != Casillero.LIBRE; i++) {
            casilleroADetenerse++;
        }
        pintarHaciaDiagonalArribaIzquierda(fila, columna, casilleroADetenerse);
    }

    private void comprobarArribaDerechaPintarCasillero(int fila, int columna) {
        int casilleroADetenerse = 0;
        for (int i = 0; this.matrizEnglobadora[fila - i][columna + i] != tiroActual && this.matrizEnglobadora[fila - i][columna + i] != Casillero.LIBRE; i++) {
            casilleroADetenerse++;
        }
        pintarHaciaDiagonalArribaDerecha(fila, columna, casilleroADetenerse);
    }

    private void comprobarAbajoIzquierdaPintarCasillero(int fila, int columna) {
        int casilleroADetenerse = 0;
        for (int i = 0; this.matrizEnglobadora[fila + i][columna - i] != tiroActual && this.matrizEnglobadora[fila + i][columna - i] != Casillero.LIBRE; i++) {
            casilleroADetenerse++;
        }
        pintarHaciaDiagonalAbajoIzquierda(fila, columna, casilleroADetenerse);
    }

    private void comprobarAbajoDerechaPintarCasillero(int fila, int columna) {
        int casilleroADetenerse = 0;
        for (int i = 0; this.matrizEnglobadora[fila + i][columna + i] != tiroActual && this.matrizEnglobadora[fila + i][columna + i] != Casillero.LIBRE; i++) {
            casilleroADetenerse++;
        }
        pintarHaciaDiagonalAbajoDerecha(fila, columna, casilleroADetenerse);
    }

    //ARRIBA ABAJO

    private void comprobarAbajoPintarCasillero(int fila, int columna) {
        int casilleroADetenerse = 0;
        for (int i = 0; this.matrizEnglobadora[fila + i][columna] != tiroActual && this.matrizEnglobadora[fila + i][columna] != Casillero.LIBRE; i++) {
            casilleroADetenerse++;
        }
        pintarHaciaAbajo(fila, columna, casilleroADetenerse);
    }

    private void comprobarArribaPintarCasillero(int fila, int columna) {
        int casilleroADetenerse = 0;
        for (int i = 0; this.matrizEnglobadora[fila - i][columna] != tiroActual && this.matrizEnglobadora[fila - i][columna] != Casillero.LIBRE; i++) {
            casilleroADetenerse++;
        }
        pintarHaciaArriba(fila, columna, casilleroADetenerse);
    }

    //COMPROBAR IZQUIERDA DERECHA
    private void comprobarDerechaPintarCasillero(int fila, int columna) {
        int casilleroADetenerse = 0;
        for (int i = 0; this.matrizEnglobadora[fila][columna + i] != tiroActual && this.matrizEnglobadora[fila][columna + i] != Casillero.LIBRE; i++) {
            casilleroADetenerse++;
        }
        pintarHaciaLaDerecha(fila, columna, casilleroADetenerse);

    }


    private void comprobarIzquierdaPintarCasillero(int fila, int columna) {
        int casilleroADetenerse = 0;
        for (int i = 0; this.matrizEnglobadora[fila][columna - i] != tiroActual && this.matrizEnglobadora[fila][columna - i] != Casillero.LIBRE; i++) {
            casilleroADetenerse++;
        }
        pintarHaciaIzquierda(fila, columna, casilleroADetenerse);
    }


    //metodos pintar arriba abajo


    private void pintarHaciaArriba(int fila, int columna, int casilleroADetenerse) {
        for (int i = 0; i <= casilleroADetenerse; i++) {
            this.matrizEnglobadora[fila - i][columna] = tiroActual;
        }
    }

    private void pintarHaciaAbajo(int fila, int columna, int casilleroADetenerse) {
        for (int i = 0; i <= casilleroADetenerse; i++) {
            this.matrizEnglobadora[fila + i][columna] = tiroActual;
        }
    }

    //metodos pintar derecha izquierda

    private void pintarHaciaLaDerecha(int fila, int columna, int casilleroADetenerse) {
        for (int i = 0; i <= casilleroADetenerse; i++) {
            this.matrizEnglobadora[fila][columna + i] = tiroActual;
        }
    }

    private void pintarHaciaIzquierda(int fila, int columna, int casilleroADetenerse) {
        for (int i = 0; i <= casilleroADetenerse; i++) {
            this.matrizEnglobadora[fila][columna - i] = tiroActual;
        }
    }


    //METODOS PINTAR DIAGONALES
    private void pintarHaciaDiagonalArribaIzquierda(int fila, int columna, int casilleroADetenerse) {
        for (int i = 0; i <= casilleroADetenerse; i++) {
            this.matrizEnglobadora[fila - i][columna - i] = tiroActual;
        }
    }

    private void pintarHaciaDiagonalArribaDerecha(int fila, int columna, int casilleroADetenerse) {
        for (int i = 0; i <= casilleroADetenerse; i++) {
            this.matrizEnglobadora[fila - i][columna + i] = tiroActual;
        }
    }

    private void pintarHaciaDiagonalAbajoIzquierda(int fila, int columna, int casilleroADetenerse) {
        for (int i = 0; i <= casilleroADetenerse; i++) {
            this.matrizEnglobadora[fila + i][columna - i] = tiroActual;
        }
    }

    private void pintarHaciaDiagonalAbajoDerecha(int fila, int columna, int casilleroADetenerse) {
        for (int i = 0; i <= casilleroADetenerse; i++) {
            this.matrizEnglobadora[fila + i][columna + i] = tiroActual;
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

    /**
     * post: indica si el juego terminó porque no existen casilleros vacíos o
     * ninguno de los jugadores puede colocar una ficha.
     */
    public boolean termino() {
        int fichasOcupadas = 0;
        for (int i = 0; i < this.matrizReversi.length; i++) {
            for (int j = 0; j < this.matrizReversi[i].length; j++) {
                if (this.matrizEnglobadora[i + 1][j + 1] != Casillero.LIBRE) {
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
