package juego;

import jugadores.Croupier;
import jugadores.Jugador;

public class ResultadoRonda {

	private Jugador[] jugadores;
	private Croupier croupier;
	
	public ResultadoRonda(Partida partida) {
	    this.jugadores = partida.getJugadores();
	    this.croupier = partida.getCroupier();
	}
	
	/**
	 * Función para determinar el mejor jugador
	 * 
	 * @return jugador con mejor puntuación o null si todos se pasan
	 */
	private Jugador mejorJugador() {
	    Jugador ganador = null;
	    int mejorPuntuacion = 0;
	    int puntosCroupier = croupier.calcularPuntuacion();

	    // Recorremos los jugadores para encontrar el que tiene la mejor puntuación sin pasarse de 21
	    for (Jugador j : jugadores) {
	        int puntos = j.calcularPuntuacion();

	        if (puntos <= 21 && puntos > mejorPuntuacion) {
	            mejorPuntuacion = puntos;
	            ganador = j;
	        }
	    }
	    
	    // Comprobamos si el croupier tiene mejor puntuación que el mejor jugador
	    if (puntosCroupier <= 21 && puntosCroupier > mejorPuntuacion) {
	        mejorPuntuacion = puntosCroupier;
	    }
	    
	    return ganador;
	}
	
	/**
	 * Comprueba si existe empate entre jugadores o con el croupier
	 * en la mejor puntuación de la ronda.
	 * 
	 * @param mejorPuntuacion puntuación más alta obtenida
	 * @return true si hay empate, false en caso contrario
	 */
	private boolean empate(int mejorPuntuacion) {
	    int contador = 0;

	    // Contamos cuántos jugadores tienen la mejor puntuación
	    for (Jugador j : jugadores) {
	        if (j.calcularPuntuacion() == mejorPuntuacion && j.calcularPuntuacion() <= 21) {
	            contador++;
	        }
	    }

	    // Sumamos el croupier si tiene la mejor puntuación y no se pasó
	    if (croupier.calcularPuntuacion() == mejorPuntuacion && croupier.calcularPuntuacion() <= 21) {
	        contador++;
	    }

	    // Si hay más de uno, hay empate
	    return contador > 1;
	}
	
	private void mostrarMarcador() {
	    System.out.println("\n===== MARCADOR =====");
	    for (Jugador j : jugadores) {
	        System.out.println(j.getNombre() + " → " + j.getVictorias());
	    }
	    System.out.println("Croupier → " + croupier.getVictorias());
	    System.out.println("===================\n");
	}
	
	/**
	 * Calcula el resultado final de la ronda comparando
	 * las puntuaciones de los jugadores y del croupier.
	 * 
	 * Determina si hay empate, si gana el croupier o si gana
	 * el mejor jugador, y actualiza el marcador de victorias.
	 */
	public void calcularGanador() {
		
	    Jugador ganador = mejorJugador();
	    int puntosCroupier = croupier.calcularPuntuacion();
	    
	    //Obtenemos la mejor puntuación entre los jugadores para compararla luego con el croupier
	    int mejorPuntuacion = (ganador != null) ? ganador.calcularPuntuacion() : 0;
	    
	    // 1 - Todos se pasan:
	    if (ganador == null && puntosCroupier > 21) {
	        System.out.println("\nTodos se pasaron");
	    } 
	    // 2 - Empate
	    else if (empate(mejorPuntuacion)) {
	    	System.out.println("\nEmpate");
	    }
	    // 3 - Gana croupier o jugador
	    else if (puntosCroupier <= 21 && puntosCroupier > mejorPuntuacion) {
	        croupier.sumarVictoria();
	        System.out.printf("\nGana el Croupier con %d puntos", puntosCroupier);
	    } else {
	        ganador.sumarVictoria();
	        System.out.printf("\nGana %s con %d puntos", ganador.getNombre(), mejorPuntuacion);
	    }

	    mostrarMarcador();
	}
}
