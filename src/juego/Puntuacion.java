package juego;

import jugadores.Croupier;
import jugadores.Jugador;

public class Puntuacion {
	private Jugador[] jugadores;
	
	public Puntuacion(Jugador[] jugadores) {
		this.jugadores = jugadores;
	}
	
    public void mostrarPuntuaciones() {
        System.out.println("\n--- Puntuaciones ---");
        for (Jugador j : jugadores) {
            System.out.println(j.getNombre() + ": " + j.calcularPuntuacion());
        }
    }
    
    public Jugador determinarGanador(Croupier croupier) {
        Jugador ganador = null;
        int mejorPuntuacion = 0;

        // Comprobamos todos los jugadores
        for (Jugador j : jugadores) {
            int puntos = j.calcularPuntuacion();
            if (puntos <= 21 && puntos > mejorPuntuacion) {
                ganador = j;
                mejorPuntuacion = puntos;
            }
        }

        // Comparamos con el croupier
        int puntosCroupier = croupier.calcularPuntuacion();
        if (puntosCroupier <= 21 && puntosCroupier > mejorPuntuacion) {
            ganador = croupier;
            mejorPuntuacion = puntosCroupier;
        }
        
        return ganador;
    }
}
