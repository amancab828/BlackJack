package juego;

import cartas.Carta;
import jugadores.AccionJugador;
import jugadores.Croupier;
import jugadores.Jugador;

public class Ronda {

	private Jugador[] jugadores;
	private Croupier croupier;
	private Puntuacion puntuacion;
	
	public Ronda(Partida partida) {
	    this.jugadores = partida.getJugadores();  
	    this.croupier = partida.getCroupier();    
	    this.puntuacion = new Puntuacion(jugadores); 
	}
	
	public void repartirInicial() {
		Carta carta;
	    for (int i = 0; i < 2; i++) {
	        for (Jugador j : jugadores) {
	        	carta = j.getBaraja().robarCarta();
	            j.recibirCarta(carta);
	        }
	        carta = croupier.getBaraja().robarCarta();
	        croupier.recibirCarta(carta);
	    }
	}
	
	public void jugar() {
		AccionJugador accion;
        Carta carta;
	    System.out.println("\n======= Comienza la ronda =======");

	    // Reparto inicial
	    repartirInicial();

	    // Vemos la carta visible del croupier
	    System.out.println("\nCartas del Croupier:");
	    croupier.mostrarCartaVisible();
	    
	    // Turno de cada jugador
	    for (Jugador jugador : jugadores) {
	        boolean turnoActivo = true;
	        System.out.println("\n-------------------------------");
	        System.out.println("Turno de " + jugador.getNombre());
	        while (turnoActivo && !jugador.getEliminado()) {
	        	// Mostrar cartas y opciones
	            jugador.mostrarCartas();
	            accion = jugador.decidirAccion();
	            switch (accion) {
	                case PEDIR -> {
	                	carta = jugador.getBaraja().robarCarta();
	                	jugador.recibirCarta(carta);
	                	jugador.mostrarCarta(carta); 
	                }
	                case PLANTARSE -> turnoActivo = false;
	            }
	            // Comprobar si el jugador se ha pasado
	            if (jugador.getEliminado()) {
	                System.out.println(jugador.getNombre() + " se ha pasado");
	                turnoActivo = false;
	            }
	        }
	    }

	    // Turno automático del croupier
	    System.out.println("\nTurno del Croupier");
	    while (croupier.calcularPuntuacion() < 17) {
	        carta = croupier.getBaraja().robarCarta();
	        croupier.recibirCarta(carta);
	        System.out.println("Croupier roba:");
	        croupier.mostrarCarta(carta);
	    }
	    System.out.println("Mano final del Croupier:");
	    croupier.mostrarCartas();

	    // Resultado
	    Jugador ganador = puntuacion.determinarGanador(croupier);

	    if (ganador != null) {
	        System.out.printf("\n🏆 Ganador: %s con %d puntos", ganador.getNombre(), ganador.calcularPuntuacion());
	    } else {
	        System.out.println("\nTodos se pasaron.");
	    }
	}
}
