package juego;

public class Pincel {

    private Examinador examinador;
    private Casillero[][] matrizEnglobadora;
    private Animacion animaciones;

    public Pincel(Casillero[][] matrizEnglobadora, Examinador examinador, Animacion animaciones) {
        this.matrizEnglobadora = matrizEnglobadora;
        this.examinador = examinador;
        this.animaciones = animaciones;
    }

    /**
     * pre : fila está en el intervalo [1, contarFilas()], columnas está en el
     * intervalo [1, contarColumnas()]
     *
     * @param fila
     * @param columna
     * @param direccion_fila    :recibe valores entre 1 y -1, para indicar para que lado
     *                          pintar casilleros, en caso de ser 1 sera para abajo y en
     *                          viceversa para arriba
     * @param direccion_columna :valores entre 1 y -1, para indicar para que lado pintar
     *                          casilleros, en caso de ser 1 sera para la derecha y en
     *                          viceversa para izquierda post: coloca ficha del turno actual
     *                          en los casilleros que sean del tiro oponente hasta toparse con
     *                          un tiroActual o llegue a los limites del tablero
     */

    public void pintarCasilleros(int fila, int columna, int direccion_fila,
                                  int direccion_columna, Casillero tiroActual, Casillero tiroOponente) {

        if (examinador.hayFichaEnElMedio(fila, columna, direccion_fila, direccion_columna, tiroActual)) {
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
}
