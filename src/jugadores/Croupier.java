package jugadores;

/**
 * Representa al croupier del Blackjack.
 */
public class Croupier extends Jugador {

    public Croupier() {
        super("Croupier");
    }

    @Override
    public void jugarTurno() {
        // regla típica: pedir hasta 17
    }
}