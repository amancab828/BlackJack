package juego;

import cartas.Baraja;
import cartas.Carta;
import jugadores.*;
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

    /**
     * Constructor de la partida.
     * @param modo modo de juego seleccionado
     */
    public Partida(ModoJuego modo) {
        this.modo = modo;
        this.baraja = new Baraja();
        this.jugadores = new ArrayList<>();
        configurarJugadores();
    }

    /**
     * Configura los jugadores según el modo de juego.
     */
    private void configurarJugadores() {
        switch (modo) {
            case MULTIJUGADOR -> configurarMultijugador();
            case CROUPIER -> configurarContraCroupier();
            case IA -> configurarContraIA();
        }
    }

    /**
     * Configuración del modo multijugador.
     */
    private void configurarMultijugador() {
        int num = Consola.leerInt("¿Cuántos jugadores? ");

        for (int i = 1; i <= num; i++) {
            String nombre = Consola.leerString("Nombre del jugador " + i + ": ");
            jugadores.add(new Jugador(nombre));
        }
    }

    /**
     * Configuración del modo contra croupier.
     */
    private void configurarContraCroupier() {
        String nombre = Consola.leerString("Nombre del jugador: ");
        jugadores.add(new Jugador(nombre));
        jugadores.add(new Croupier());
    }

    /**
     * Configuración del modo contra IA.
     */
    private void configurarContraIA() {
        String nombre = Consola.leerString("Nombre del jugador: ");
        jugadores.add(new Jugador(nombre));
        jugadores.add(new IAJugador("IA"));
    }

    /**
     * Reparte dos cartas iniciales a cada jugador.
     */
    public void repartirInicial() {
        for (int i = 0; i < 2; i++) {
            for (Jugador j : jugadores) {
                Carta carta = baraja.robarCarta();
                j.recibirCarta(carta);
            }
        }
    }

    /**
     * Ejecuta la partida completa.
     */
    public void jugar() {
        System.out.println("\n--- Comienza la partida ---");

        repartirInicial();

        // Turnos de jugadores
        for (Jugador j : jugadores) {
            System.out.println("\nTurno de " + j.getNombre());
            j.jugarTurno();
        }

        mostrarPuntuaciones();
        Jugador ganador = determinarGanador();

        if (ganador != null) {
            System.out.println("\n🏆 Ganador: " + ganador.getNombre());
        } else {
            System.out.println("\nEmpate o todos se pasaron.");
        }
    }

    /**
     * Muestra la puntuación de todos los jugadores.
     */
    private void mostrarPuntuaciones() {
        System.out.println("\n--- Puntuaciones ---");
        for (Jugador j : jugadores) {
            System.out.println(j.getNombre() + ": " + j.calcularPuntuacion());
        }
    }

    /**
     * Determina el ganador de la partida.
     * @return jugador ganador o null si no hay
     */
    public Jugador determinarGanador() {
        return jugadores.stream()
                .filter(j -> !j.estaEliminado())
                .max(Comparator.comparingInt(Jugador::calcularPuntuacion))
                .orElse(null);
    }
}