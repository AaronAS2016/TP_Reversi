package juego;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
        escenario.setScene(tablero);
        WindowStyle.allowDrag(pantallaTablero, escenario);
        crearControles();
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

        jugador1.setText(juego.obtenerJugadores(1));
        jugador2.setText(juego.obtenerJugadores(2));

        jugadorActual = (Label) tablero.lookup("#txtJugadorActual");
        jugadorActual.setText(juego.obtenerJugadorActual());
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
