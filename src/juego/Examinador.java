package juego;

public class Examinador {

    private Pincel pincel;

    public boolean examinarTablero(Casillero[][] matrizReversi, int fila, int columna, Casillero tiroOponente, Casillero tiroActual, Pincel pincel) {

        this.pincel = pincel;

        boolean sePuedeColocarFicha = false;


        if (fila + 1 < matrizReversi.length) {
            if (matrizReversi[fila + 1][columna] == tiroOponente) {
                sePuedeColocarFicha = hayFichaEnElMedio(fila, columna, 1, 0, tiroActual, matrizReversi);
            }
        }

        if (fila - 1 > 0) {
            if (matrizReversi[fila - 1][columna] == tiroOponente) {
                sePuedeColocarFicha = hayFichaEnElMedio(fila, columna, -1, 0, tiroActual, matrizReversi);
            }
        }

        if (columna + 1 < matrizReversi.length) {
            if (matrizReversi[fila][columna + 1] == tiroOponente) {
                sePuedeColocarFicha = hayFichaEnElMedio(fila, columna, 0, 1, tiroActual, matrizReversi);
            }
        }

        if (columna - 1 > 0) {
            if (matrizReversi[fila][columna - 1] == tiroOponente) {
                sePuedeColocarFicha = hayFichaEnElMedio(fila, columna, 0, -1, tiroActual, matrizReversi);
            }
        }

        if (fila - 1 > 0 && columna - 1 > 0) {
            if (matrizReversi[fila - 1][columna - 1] == tiroOponente) {
                sePuedeColocarFicha = hayFichaEnElMedio(fila, columna, -1, -1, tiroActual, matrizReversi);
            }
        }

        if (fila + 1 < matrizReversi.length && columna + 1 < matrizReversi.length) {
            if (matrizReversi[fila + 1][columna + 1] == tiroOponente) {
                sePuedeColocarFicha = hayFichaEnElMedio(fila, columna, 1, 1, tiroActual, matrizReversi);
            }
        }

        if (fila - 1 > 0 && columna + 1 < matrizReversi.length) {
            if (matrizReversi[fila - 1][columna + 1] == tiroOponente) {
                sePuedeColocarFicha = hayFichaEnElMedio(fila, columna, -1, 1, tiroActual, matrizReversi);
            }
        }

        if (fila + 1 < matrizReversi.length && columna - 1 > 0) {
            if (matrizReversi[fila + 1][columna - 1] == tiroOponente) {
                sePuedeColocarFicha = hayFichaEnElMedio(fila, columna, 1, -1, tiroActual, matrizReversi);
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
     * @param direccion_fila    :recibe valores entre 1 y -1, para indicar para que lado
     *                          realiza el analisis, en caso de ser 1 sera para abajo y en
     *                          viceversa para arriba
     * @param direccion_columna :valores entre 1 y -1, para indicar para que lado realiza el
     *                          analisis, en caso de ser 1 sera para la derecha y en viceversa
     *                          para izquierda post: devuelve si hay una ficha del turno
     *                          actual contiguos al casillero
     */
    public boolean hayFichaEnElMedio(int fila, int columna,
                                     int direccion_fila, int direccion_columna, Casillero colorPincel, Casillero[][] matrizReversi) {
        boolean hayFichaEnMedio = false;
        while (pincel.puedePintar(fila,columna, matrizReversi) && !hayFichaEnMedio) {

            hayFichaEnMedio = (matrizReversi[fila][columna] == colorPincel);

            fila += direccion_fila;
            columna += direccion_columna;

        }
        return hayFichaEnMedio;
    }

}
