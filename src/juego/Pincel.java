package juego;

public class Pincel {

    private Examinador examinador;
    private Animacion animaciones;
    private Casillero[][] matrizReversi;

    public Pincel(Examinador examinador, Animacion animaciones, Casillero[][] matrizReversi) {
        this.examinador = examinador;
        this.animaciones = animaciones;
        this.matrizReversi = matrizReversi;
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
                                  int direccion_columna, Casillero pincel, Casillero tiroOponente) {

        if (examinador.hayFichaEnElMedio(fila, columna, direccion_fila, direccion_columna, pincel)) {
            while (puedePintar(fila, columna) && !encontroActual(fila, columna, pincel)){

                if (matrizReversi[fila][columna] == tiroOponente) {
                    this.matrizReversi[fila][columna] = pincel;
                    animaciones.agregarAnimaciones(fila, columna);
                }

                fila += direccion_fila;
                columna += direccion_columna;
            }

        }

    }

    private boolean encontroActual(int fila, int columna, Casillero tiroActual) {

        boolean encontroActual = false;

        if(puedePintar(fila, columna)){
            encontroActual = (matrizReversi[fila][columna] == tiroActual);
        }
       return encontroActual;
    }

    public boolean puedePintar(int fila, int columna){
        return (fila  >= 0 && columna >= 0 && fila < matrizReversi.length && columna < matrizReversi.length);
    }

}
