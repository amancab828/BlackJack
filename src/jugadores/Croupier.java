package jugadores;

import cartas.Baraja;
import cartas.Carta;

/**
 * Representa al croupier del Blackjack.
 */
public class Croupier extends Jugador {
	
    public Croupier(Baraja baraja) {
        super("Croupier", baraja);
    }
    
    public void mostrarCartaVisible() {
        mostrarCarta(mano.get(0));

        System.out.println("┌─────────┐");
        System.out.println("|░░░░░░░░░|"); 
        System.out.println("|░░░░░░░░░|");
        System.out.println("|░░░░░░░░░|");
        System.out.println("|░░░░░░░░░|");
        System.out.println("|░░░░░░░░░|");
        System.out.println("└─────────┘");
        
    }
    
    public void jugarTurno() {
        System.out.println("\nTurno del Croupier");
        while (calcularPuntuacion() < 17) {
            Carta carta = baraja.robarCarta();
            recibirCarta(carta);
            System.out.println("Croupier roba: " + carta);
        }
        mostrarCartas();
        if (calcularPuntuacion() > 21) {
            System.out.println("Croupier se ha pasado 😵");
        }
    }
}