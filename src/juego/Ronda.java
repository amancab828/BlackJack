package juego;

import cartas.Baraja;
import cartas.Carta;
import jugadores.AccionJugador;
import jugadores.Croupier;
import jugadores.Jugador;

public class Ronda {

	private Jugador[] jugadores;
	private Croupier croupier;
	private Baraja baraja;
	
	public Ronda(Partida partida) {
	    this.jugadores = partida.getJugadores();
	    this.croupier = partida.getCroupier();
	    this.baraja = new Baraja();
	}
	
	// Reparto inicial, 2 cartas a cada jugador y al croupier
	private void repartirInicial() {
	    for (int i = 0; i < 2; i++) {
	        for (Jugador j : jugadores) {
	            j.recibirCarta(baraja.robarCarta());
	        }
	        croupier.recibirCarta(baraja.robarCarta());
	    }
	}

    // 1 - Mostrar cartas de todos al inicio de la vuelta
	private void mostrarCartasJugadores() {
	    for (Jugador j : jugadores) {
	        if (!j.getEliminado()) {
	            j.mostrarCartas();
	        }
	    }
	}
	
    //2️ - Preguntar a todos qué quieren hacer solo si no está eliminado ni plantado
	private AccionJugador[] decidirAcciones() {
	    AccionJugador[] decisiones = new AccionJugador[jugadores.length];
	    
	    for (int i = 0; i < jugadores.length; i++) {
	        Jugador j = jugadores[i];
	        
	        if (!j.getEliminado() && !j.getPlantado()) {
	            // Preguntamos su acción
	            decisiones[i] = j.decidirAccion();
	            
	            // Si decide plantarse, lo marcamos para no volver a preguntar
	            if (decisiones[i] == AccionJugador.PLANTARSE) {
	                j.setPlantado(true);
	            }
	        }
	    }
	    
	    return decisiones;
	}
	
    // 3️ - Repartir cartas a quienes pidieron
	private void repartirCartas(AccionJugador[] decisiones) {
	    for (int i = 0; i < jugadores.length; i++) {
	        Jugador j = jugadores[i];
	        
	        // Si el jugador decidió pedir, le damos una carta y la mostramos
	        if (decisiones[i] == AccionJugador.PEDIR) {
	        	// Robamos una carta de la baraja y se la damos al jugador
	            Carta carta = baraja.robarCarta();
	            j.recibirCarta(carta);
	            
	            // Mostramos la carta que ha robado el jugador
	            System.out.println("\n" + j.getNombre() + " roba:");
	            j.mostrarCarta(carta);
	            
	            // Si el jugador se ha pasado
	            if (j.getEliminado()) {
	            	j.setEliminado(true);
	                System.out.println(j.getNombre() + " se ha pasado.");
	            }
	        }
	    }
	}
	
	/**
	 * Gestiona los turnos de todos los jugadores hasta que
	 * todos se planten o sean eliminados.
	 */
	private void turnoJugadores() {
	    boolean todosPlantados;

	    do {
	        todosPlantados = true;
	        
	        // 1️ - Mostrar cartas de todos al inicio de la vuelta
	        System.out.println("\n===== NUEVA VUELTA =====");
	        mostrarCartasJugadores();
	        
	        // 2️ - Preguntar a todos qué quieren hacer
	        AccionJugador[] decisiones = decidirAcciones();
	        for (AccionJugador decision : decisiones) {
	            if (decision == AccionJugador.PEDIR) {
	            	todosPlantados = false;
	            }
	        }

	        // 3️ - Repartir cartas a quienes pidieron y las mostramos
	        repartirCartas(decisiones);
	    } while (!todosPlantados);
	}

	/**
	 * Ejecuta el turno automático del croupier,
	 * que roba cartas hasta alcanzar al menos 17 puntos.
	 */
	private void turnoCroupier() {
	    Carta carta;
	    System.out.println("\n-------------------------------");
	    System.out.println("Turno del Croupier");

	    while (croupier.calcularPuntuacion() < 17) {
	        carta = baraja.robarCarta();
	        croupier.recibirCarta(carta);
	        System.out.println("Croupier roba:");
	        croupier.mostrarCarta(carta);
	    }

	    System.out.println("Mano final del Croupier:");
	    croupier.mostrarCartas();
	}
	
	/**
	 * Inicia y ejecuta una ronda completa de Blackjack.
	 * <p>
	 *   Reparto inicial.
	 *   Mostrar la carta visible del croupier.
	 *   Turnos de todos los jugadores.
	 *   Turno del croupier.
	 *   Determinación del ganador de la ronda y actualización del marcador.
	 * </p>
	 */
	public void jugar() {
	    System.out.println("\n======= Comienza la ronda =======");

	    repartirInicial();
	    croupier.mostrarCartaCroupier();
	    turnoJugadores();
	    turnoCroupier();
	}
	
	/**
	 * Reinicia la ronda para poder jugar otra partida.
	 * <p>
	 *   Crea una nueva baraja barajada.
	 *   Limpia la mano de todos los jugadores y del croupier.
	 *   Marca a todos los jugadores y al croupier como no eliminados.
	 * </p>
	 */
	public void reiniciar() {
	    baraja = new Baraja();
	    for (Jugador j : jugadores) {
	        j.limpiarMano();
	        j.setEliminado(false);
	    }
	    croupier.limpiarMano();
	    croupier.setEliminado(false);
	}
}
