package jugadores;

import cartas.Baraja;

/**
 * Representa al croupier del Blackjack
 * <p>
 *    El croupier tiene un nombre fijo "Croupier" y sigue las reglas estándar.
 *    Pide carta si su puntuación es menor que 17 y se planta si es 17 o más.
 * </p>
 */
public class Croupier extends Jugador {
	
    public Croupier(Baraja baraja) {
        super("Croupier", baraja);
    }
    
    /**
     * Muestra la primera carta del croupier (la visible) y la oculta
     */
    public void mostrarCartaCroupier() {
    	System.out.println("\nCartas del Croupier:");
        mostrarCarta(mano.get(0));

        System.out.println("┌─────────┐");
        System.out.println("|░░░░░░░░░|"); 
        System.out.println("|░░░░░░░░░|");
        System.out.println("|░░░░░░░░░|");
        System.out.println("|░░░░░░░░░|");
        System.out.println("|░░░░░░░░░|");
        System.out.println("└─────────┘");
        
    }
    
    /**
     * Decide la acción del croupier según las reglas del Blackjack.
     * <p>
     * El croupier pide carta si su puntuación es menor a 17, 
     * y se planta en caso contrario.
     * </p>
     * @return la acción que realizará el croupier (PEDIR o PLANTARSE)
     */
    public AccionJugador decidirAccion() {
        int puntos = calcularPuntuacion();
        if (puntos < 17) {
            return AccionJugador.PEDIR;
        } else {
            return AccionJugador.PLANTARSE;
        }
    }
}