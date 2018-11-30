package juego;

public class Examinador {

    private Casillero[][] matrizEnglobadora;

    public Examinador(Casillero[][] matrizEnglobadora){
        this.matrizEnglobadora = matrizEnglobadora;
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
    public boolean hayFichaEnElMedio(int fila, int columna,
                                     int direccion_fila, int direccion_columna, Casillero tiroActual) {
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

}
