package juego;

import cartas.Baraja;
import jugadores.*;
import cartas.Carta;
import main.Consola;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Controla el flujo de una partida de Blackjack.
 */
public class Partida {

    private Baraja baraja;
    private List<Jugador> jugadores;
    private ModoJuego modo;
    private Croupier croupier;
    private Consola consola = new Consola();

    //Constructor
    public Partida(ModoJuego modo) {
        this.modo = modo;
        this.baraja = new Baraja();
        this.jugadores = new ArrayList<>();
        this.croupier = new Croupier(baraja);
        configurarJugadores();
    }
    
    //Getters
    public Jugador[] getJugadores() {
        return jugadores.toArray(new Jugador[0]);
    }
    public Croupier getCroupier() {
        return croupier;
    }

    
    //Configuración de jugadores según el modo seleccionado
    private void configurarJugadores() {
        switch (modo) {
            case MULTIJUGADOR -> configurarMultijugador();
            case CROUPIER -> configurarContraCroupier();
            case IA -> configurarContraIA();
        }
    }

    private void configurarMultijugador() {
        int num = consola.leerInt("¿Cuántos jugadores? ");

        for (int i = 1; i <= num; i++) {
            String nombre = consola.leerString("Nombre del jugador " + i + ": ");
            Jugador jugador = new Jugador(nombre, baraja);
            jugadores.add(jugador);
        }
    }

    private void configurarContraCroupier() {
        String nombre = consola.leerString("Nombre del jugador: ");
        
        // Creamos el jugador
        Jugador jugador = new Jugador(nombre, baraja);
        jugadores.add(jugador);
    }

    private void configurarContraIA() {
        String nombre = consola.leerString("Nombre del jugador: ");

        // Creamos el jugador
        Jugador jugador = new Jugador(nombre, baraja);
        jugadores.add(jugador);
        
        // Creamos la IA
        Jugador jugadorIA = new Jugador("IA", baraja);
        jugadores.add(jugadorIA);
    }
}