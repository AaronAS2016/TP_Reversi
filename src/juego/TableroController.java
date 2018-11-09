package juego;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TableroController{
    private static final int TAMANIO_FICHA = 80;
    private static final double DIMENSION_BOTON = 20;

    private Reversi juego;
    private BorderPane panel;
    private GridPane grilla;
    private Stage escenario;

    private Scene tablero;
    private Scene menu;

    private Label cantidadFichasNegras;
    private Label cantidadFichasBlancas;
    private Label jugadorActual;

    public TableroController(Reversi juego, Stage ventana, Scene menu){
        this.juego = juego;
        this.escenario = ventana;
        this.menu = menu;
    }

    public void mostrar() throws IOException {
        Parent pantallaTablero =  FXMLLoader.load(getClass().getResource("./../views/tablero.fxml"));
        tablero = new Scene(pantallaTablero, 800,680);
        tablero.getStylesheets().add(
                getClass().getResource("/views/styles.css").toExternalForm());
        escenario.setScene(tablero);
        WindowStyle.allowDrag(pantallaTablero, escenario);

        crearControles();
        dibujar();
    }

    public void dibujar() {

        grilla.getChildren().clear();

        for (int fila = 1; fila <= juego.contarFilas(); fila++) {

            for (int columna = 1; columna <= juego.contarColumnas(); columna++) {

                dibujarFicha(fila, columna);
                //dibujarBoton(fila, columna);
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


        Image image = new Image("./img/cruz.png");
        ImageView dibujoCasillero = new ImageView(image);
        dibujoCasillero.setFitHeight(grilla.getWidth() / juego.contarColumnas() );
        dibujoCasillero.setFitWidth(grilla.getHeight() / juego.contarFilas());

        dibujoCasillero.getStyleClass().add("image-view-wrapper");

        dibujar(dibujoCasillero, fila, columna);
    }

    /**
     * post: dibuja el boton en el casillero indicado por fila y columna,
     *       si es que se puede colocar una ficha.
     *
     * @param fila
     * @param columna
     */
   /* private void dibujarBoton(int fila, int columna) {

        if (juego.puedeColocarFicha(fila, columna)) {

            Button botonColocarFicha = new Button();
            botonColocarFicha.setMinSize(DIMENSION_BOTON, DIMENSION_BOTON);
            botonColocarFicha.setMaxSize(DIMENSION_BOTON, DIMENSION_BOTON);
            botonColocarFicha.setOnAction(new ColocarFicha(this, juego, fila, columna));

            dibujar(botonColocarFicha, fila, columna);
        }
    }*/

    private void dibujar(Node elemento, int fila, int columna) {

        GridPane.setHalignment(elemento, HPos.CENTER);
        GridPane.setValignment(elemento, VPos.CENTER);

        grilla.add(elemento, columna - 1, fila - 1);
    }

    public void crearControles(){
        Button btnCerrar = (Button) tablero.lookup("#btnCerrar");
        Button btnVolverMenu = (Button) tablero.lookup("#btnVolver");

        btnVolverMenu.addEventHandler(MouseEvent.MOUSE_CLICKED, new VolverAlMenuNavegacion());
        btnCerrar.addEventHandler(MouseEvent.MOUSE_CLICKED, new Cerrar());

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
    public class Cerrar implements EventHandler<Event> {
        @Override
        public void handle(Event evt) {
            Platform.exit();
            System.exit(0);
        }
    }

    public class VolverAlMenuNavegacion implements EventHandler<Event> {
        @Override
        public void handle(Event evt) {
            escenario.setScene(menu);
        }
    }


}
