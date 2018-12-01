package juego;

public class Validador {

    /* Constantes del Tablero */
    private static final int MAXIMO_DEL_TABLERO = 10;
    private static final int MINIMO_DEL_TABLERO = 4;
    private static final int MAXIMO_DE_CARACTERES = 10;
    private static final int MINIMO_DE_CARACTERES = 3;

    private Reversi juego;


    public Validador(Reversi reversi){
        juego = reversi;
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

    public void validarJugadores(String fichasNegras, String fichasBlancas) {
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

    public void validarTablero(int dimensionTablero) {
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
    public void validarPosicion(int fila, int columna, Casillero[][] matrizReversi) {
        if (fila < 0 || columna < 0 || fila >= matrizReversi.length
                || columna >= matrizReversi.length) {
            throw new Error("Posición fuera del tablero");
        }
    }

    /**
     * @param fila
     * @param columna
     *            post: ejecuta una error en caso de no poder colocar ficha en
     *            el casillero
     */
    public void validarPuedeColocar(int fila, int columna) {
        if (!juego.puedeColocarFicha(fila, columna)) {
            throw new Error("No se puede colocar ficha en este casillero");
        }
    }
}
