package juego;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.animation.RotateTransition;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.animation.TranslateTransition;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.awt.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class TableroController{


    private Reversi juego;
    private GridPane grilla;
    private Stage escenario;

    private Scene tablero;
    private Scene menu;

    private Label cantidadFichasNegras;
    private Label cantidadFichasBlancas;
    private Label jugadorActual;

    private Parent pantallaResultado;

    public TableroController(Reversi juego, Stage ventana, Scene menu){
        this.juego = juego;
        this.escenario = ventana;
        this.menu = menu;
    }

    public void mostrar() throws IOException {

        Parent pantallaTablero =  FXMLLoader.load(getClass().getResource("./../views/tablero.fxml"));
        tablero = new Scene(pantallaTablero, 800,680);
        escenario.setScene(tablero);
        escenario.getIcons().add(new Image("./img/icono.png"));
        WindowStyle.allowDrag(pantallaTablero, escenario);
        crearControles();
        dibujar();
    }

    public void dibujar() {

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

        if (juego.puedeColocarFicha(fila, columna)) {

            Button botonColocarFicha = new Button();
            botonColocarFicha.setMinSize(15, 15);
            botonColocarFicha.setMaxSize(15, 15);
            botonColocarFicha.addEventHandler(MouseEvent.MOUSE_CLICKED, new Pintar(this, juego, fila, columna, botonColocarFicha));


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

        btnVolverMenu.addEventHandler(MouseEvent.MOUSE_CLICKED, new VolverAlMenuNavegacion());
        btnCerrar.addEventHandler(MouseEvent.MOUSE_CLICKED, new Cerrar());
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
        Scene resultado = new Scene(pantallaResultado, 700, 400);
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
        WindowStyle.stageDimension(resultado.getWidth(), resultado.getHeight());
        WindowStyle.allowDrag(pantallaResultado, dialogo);
        dialogo.show();

    }

    private String crearMensajeResultado() {

        String mensajeResultado;
        if (juego.hayGanador()) {

            mensajeResultado = "¡Ganó " + juego.obtenerGanador() + "!";

        } else {

            mensajeResultado = "¡Empataron! D:";
        }

        try {
            mensajeResultado = new String(mensajeResultado.getBytes("ISO-8859-15"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        return mensajeResultado;
    }
    public class Cerrar implements EventHandler<Event> {
        @Override
        public void handle(Event evt) {
            Platform.exit();
            System.exit(0);
        }
    }

    public class Pintar implements EventHandler<Event> {
        private TableroController tablero;
        private Reversi juego;
        private int fila;
        private int columna;
        private Node btnColocar;

        public Pintar(TableroController tableroReversi, Reversi reversi,
                            int filaSeleccionada, int columnaSeleccionada, Button btn) {

            tablero = tableroReversi;
            juego = reversi;
            fila = filaSeleccionada;
            columna = columnaSeleccionada;
            btnColocar = getNodeByRowColumnIndex(fila - 1, columna - 1 , grilla);
        }
        @Override
        public void handle(Event evt) {
            juego.colocarFicha(fila, columna);


            tablero.dibujar();

            if (juego.termino()) {
                tablero.mostrarResultado();
            }
        }
    }

    public class VolverAlMenuNavegacion implements EventHandler<Event> {
        @Override
        public void handle(Event evt) {
            escenario.setScene(menu);
        }
    }

    public Node getNodeByRowColumnIndex (final int row, final int column, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> childrens = gridPane.getChildren();

        for (Node node : childrens) {
            if(gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }

        return result;
    }


}
