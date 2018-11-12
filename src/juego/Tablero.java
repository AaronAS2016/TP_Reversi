package juego;


import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class Tablero {


    private Reversi juego;
    private GridPane grilla;
    private Stage escenario;

    private Scene tablero;
    private Scene menu;

    private Label cantidadFichasNegras;
    private Label cantidadFichasBlancas;
    private Label jugadorActual;

    private Label seCambioDeTurno;
    private ImageView turnoActual;

    private Parent pantallaResultado;

    public Tablero(Reversi juego, Stage ventana, Scene menu){
        this.juego = juego;
        this.escenario = ventana;
        this.menu = menu;
    }

    public void mostrar() throws IOException {
        configuramosVentana();
        crearControles();
        dibujar();
    }

    private void configuramosVentana() throws IOException {
        Parent pantallaTablero =  FXMLLoader.load(getClass().getResource("./../views/tablero.fxml"));
        tablero = new Scene(pantallaTablero, 800,680);
        escenario.setScene(tablero);
        escenario.getIcons().add(new Image("./img/icono.png"));
        EstilizarVentana.allowDrag(pantallaTablero, escenario);
    }

    public void dibujar() {

        seCambioDeTurno.setVisible(false);

        grilla.getChildren().clear();

        for (int fila = 1; fila <= juego.contarFilas(); fila++) {

            for (int columna = 1; columna <= juego.contarColumnas(); columna++) {

                dibujarFicha(fila, columna);
                dibujarBoton(fila, columna);
            }
        }
        dibujarEstado();
    }

    /**
     * post: actualiza el estado del juego del en Tablero.
     */
    private void dibujarEstado() {

        cantidadFichasNegras.setText(String.valueOf(juego.contarFichasNegras()));
        cantidadFichasBlancas.setText(String.valueOf(juego.contarFichasBlancas()));
        jugadorActual.setText(juego.obtenerJugadorActual());
        Casillero casilleroActual = juego.obtenerTiroActual();
        Image turno = crearPintura(casilleroActual);
        turnoActual.setImage(turno);
    }

    /**
     * post: dibuja la ficha en la posición indicada por fila y columna.
     */
    private void dibujarFicha(int fila, int columna){

        Casillero casillero = juego.obtenerCasillero(fila, columna);
        int tamanio = new Double(grilla.getWidth() / juego.contarFilas()).intValue();

        Image image = crearPintura(casillero);
        if(image != null){
            ImageView dibujoCasillero = new ImageView(image);
            dibujoCasillero.setFitHeight(grilla.getWidth() / juego.contarColumnas() );
            dibujoCasillero.setFitWidth(grilla.getHeight() / juego.contarFilas());
            dibujar(dibujoCasillero, fila, columna);
        }else {
            Paint pintura = Color.TRANSPARENT;
            Rectangle dibujoCasillero = new Rectangle(tamanio, tamanio);
            dibujoCasillero.setFill(pintura);
            dibujar(dibujoCasillero, fila, columna);
        }
    }

    private Image crearPintura(Casillero casillero) {

        Image pintura;

        switch (casillero) {

            case BLANCAS:
                pintura = new Image("./img/circulo.png");
                break;

            case NEGRAS:
                pintura = new Image("./img/cruz.png");
                break;

            default:
                pintura = null;
        }

        return pintura;
    }

    /**
     * post: dibuja el boton en el casillero indicado por fila y columna,
     *       si es que se puede colocar una ficha.
     *
     * @param fila
     * @param columna
     */
   private void dibujarBoton(int fila, int columna) {
        int contarCasillerosDisponibles = juego.contarMovimientosPosibles();
        if(contarCasillerosDisponibles == 0 && !juego.termino()){
            dibujar();
            seCambioDeTurno.setVisible(true);
        }
        if (juego.puedeColocarFicha(fila, columna)) {

            Button botonColocarFicha = new Button();
            botonColocarFicha.setMinSize(15, 15);
            botonColocarFicha.setMaxSize(15, 15);
            botonColocarFicha.addEventHandler(MouseEvent.MOUSE_CLICKED, new ColocarFicha(this, juego, fila, columna, botonColocarFicha));

            dibujar(botonColocarFicha, fila, columna);
        }
    }

    private void dibujar(Node elemento, int fila, int columna) {

        GridPane.setHalignment(elemento, HPos.CENTER);
        GridPane.setValignment(elemento, VPos.CENTER);
        grilla.add(elemento, columna - 1, fila - 1);
    }

    public void crearControles(){
        Button btnCerrar = (Button) tablero.lookup("#btnCerrar");
        Button btnVolverMenu = (Button) tablero.lookup("#btnVolver");
        Button btnReiniciar = (Button) tablero.lookup("#btnReiniciar");

        btnVolverMenu.addEventHandler(MouseEvent.MOUSE_CLICKED, new CambiarEscena(menu, escenario));
        btnCerrar.addEventHandler(MouseEvent.MOUSE_CLICKED, new CerrarJuego());
        btnReiniciar.addEventHandler(MouseEvent.MOUSE_CLICKED, e->{
            juego.reiniciarJuego();
            dibujar();
        });


        cantidadFichasNegras = (Label) tablero.lookup("#txtPuntajeJugador1");
        cantidadFichasBlancas = (Label) tablero.lookup("#txtPuntajeJugador2");
        cantidadFichasNegras.setText(Integer.toString(juego.contarFichasNegras()));
        cantidadFichasBlancas.setText(Integer.toString(juego.contarFichasBlancas()));

        Label jugador1 = (Label) tablero.lookup("#txtJugador1");
        Label jugador2 = (Label) tablero.lookup("#txtJugador2");

        seCambioDeTurno = (Label) tablero.lookup("#txtCambioDeTurno");

        turnoActual = (ImageView) tablero.lookup("#labelTurno");

        jugador1.setText(juego.obtenerJugadores(1) + ":");
        jugador2.setText(juego.obtenerJugadores(2) + ":");

        jugadorActual = (Label) tablero.lookup("#txtJugadorActual");
        jugadorActual.setText(juego.obtenerJugadorActual());

        grilla = (GridPane) tablero.lookup("#grillaTablero");
    }

    public void mostrarResultado(){

        Stage dialogo = new Stage();
        try {
            pantallaResultado = FXMLLoader.load(getClass().getResource("./../views/resultado.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene resultado = new Scene(pantallaResultado, 650, 230);
        Button btnCerrar = (Button) pantallaResultado.lookup("#btnCerrar");
        btnCerrar.addEventHandler(MouseEvent.MOUSE_CLICKED, e->{
            dialogo.close();
        });
        String textoResultado = crearMensajeResultado();
        Label labelResultado = (Label) pantallaResultado.lookup("#labelResultado");
        labelResultado.setText(textoResultado);
        dialogo.initStyle(StageStyle.UNDECORATED);
        dialogo.setScene(resultado);
        dialogo.setResizable(false);
        dialogo.getIcons().add(new Image("./img/icono.png"));
        try {
            dialogo.setTitle(new String("¡TERMINO!".getBytes("ISO-8859-15"), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        EstilizarVentana.stageDimension(resultado.getWidth(), resultado.getHeight());
        EstilizarVentana.allowDrag(pantallaResultado, dialogo);
        dialogo.show();

    }

    private String crearMensajeResultado() {

        String mensajeResultado;

        if (juego.hayGanador()) {
            String ganador = juego.obtenerGanador();



            mensajeResultado = "¡Ganó " + ganador + "!";

        } else {

            mensajeResultado = "¡Empataron! O:";
        }



        return mensajeResultado;
    }


}
