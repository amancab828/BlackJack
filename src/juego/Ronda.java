package juego;

import cartas.Baraja;
import cartas.Carta;
import jugadores.AccionJugador;
import jugadores.Croupier;
import jugadores.IAJugador;
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
	
	private void repartirInicial() {
	    for (int i = 0; i < 2; i++) {
	        for (Jugador j : jugadores) {
	            j.recibirCarta(baraja.robarCarta());
	        }
	        croupier.recibirCarta(baraja.robarCarta());
	    }
	}
	
	private void calcularGanadorRonda() {

	    int mejorPuntuacion = 0;
	    Jugador ganador = null;
	    boolean empate = false;

	    // 1️ Mirar jugadores
	    for (Jugador j : jugadores) {

	        int puntos = j.calcularPuntuacion();

	        if (puntos <= 21) {

	            if (puntos > mejorPuntuacion) {
	                mejorPuntuacion = puntos;
	                ganador = j;
	                empate = false;
	            }
	            else if (puntos == mejorPuntuacion) {
	                empate = true;
	            }
	        }
	    }

	    // 2️ Mirar croupier
	    int puntosCroupier = croupier.calcularPuntuacion();

	    if (puntosCroupier <= 21) {

	        if (puntosCroupier > mejorPuntuacion) {
	            ganador = null;     // gana croupier
	            empate = false;
	        }
	        else if (puntosCroupier == mejorPuntuacion) {
	            empate = true;
	        }
	    }

	    // 3️ Resultado final
	    if (mejorPuntuacion == 0 && puntosCroupier > 21) {
	        System.out.println("\nTodos se pasaron.");
	        return;
	    }

	    if (empate) {
	        System.out.println("\nEmpate.");
	    } else if (ganador != null) {
	        ganador.sumarVictoria();
	        System.out.println("\nGana " + ganador.getNombre() +
	                " con " + ganador.calcularPuntuacion() + " puntos.");
	    } else {
	        croupier.sumarVictoria();
	        System.out.println("\nGana el Croupier con " +
	                puntosCroupier + " puntos.");
	    }
	    
	    mostrarMarcador();
	}
	
	private void mostrarMarcador() {
	    System.out.println("\n===== MARCADOR =====");
	    for (Jugador j : jugadores) {
	        System.out.println(j.getNombre() + " → " + j.getVictorias());
	    }
	    System.out.println("Croupier → " + croupier.getVictorias());
	    System.out.println("===================\n");
	}
	
	private void mostrarCartaVisibleCroupier() {
	    System.out.println("\nCartas del Croupier:");
	    croupier.mostrarCartaVisible();
	}

	//Mirar muy bien aqui esta funcion y repasarla
	private void turnoJugadores() {
	    boolean todosPlantados;

	    do {
	        todosPlantados = true;

	        // 1️⃣ Mostrar cartas de todos al inicio de la vuelta
	        System.out.println("\n===== NUEVA VUELTA =====");
	        for (Jugador j : jugadores) {
	            if (!j.getEliminado()) {
	                j.mostrarCartas();
	            }
	        }

	        // 2️⃣ Preguntar a todos qué quieren hacer
	        AccionJugador[] decisiones = new AccionJugador[jugadores.length];
	        for (int i = 0; i < jugadores.length; i++) {
	            Jugador j = jugadores[i];

	            if (!j.getEliminado()) {
	                decisiones[i] = j.decidirAccion();

	                if (decisiones[i] == AccionJugador.PEDIR) {
	                    todosPlantados = false;
	                } else {
	                    decisiones[i] = AccionJugador.PLANTARSE;
	                }
	            } else {
	                decisiones[i] = AccionJugador.PLANTARSE;
	            }
	        }

	        // 3️⃣ Repartir cartas a quienes pidieron
	        for (int i = 0; i < jugadores.length; i++) {
	            Jugador j = jugadores[i];

	            if (decisiones[i] == AccionJugador.PEDIR) {
	                Carta carta = baraja.robarCarta();
	                j.recibirCarta(carta);

	                System.out.println("\n" + j.getNombre() + " roba:");
	                j.mostrarCarta(carta);

	                if (j.getEliminado()) {
	                    System.out.println(j.getNombre() + " se ha pasado.");
	                }
	            }
	        }

	        // 4️⃣ Mostrar el estado actualizado de todos
	        System.out.println("\n===== ESTADO ACTUAL =====");
	        for (Jugador j : jugadores) {
	            j.mostrarCartas();
	        }

	    } while (!todosPlantados);
	}

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
	    mostrarCartaVisibleCroupier();
	    turnoJugadores();
	    turnoCroupier();
	    calcularGanadorRonda();
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
