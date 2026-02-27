package jugadores;

import cartas.Carta;
import interfaces.Jugable;
import java.util.*;

/**
 * Representa a un jugador de Blackjack.
 */
public class Jugador implements Jugable {

    protected String nombre;
    protected List<Carta> mano;
    protected boolean eliminado;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.mano = new ArrayList<>();
    }

    /**
     * Añade una carta a la mano.
     * @param carta carta robada
     */
    public void recibirCarta(Carta carta) {
        mano.add(carta);
    }

    @Override
    public int calcularPuntuacion() {
        int total = 0;
        int ases = 0;

        for (Carta c : mano) {
            total += c.getValor();
            if (c.getTipo().name().equals("AS")) {
                ases++;
            }
        }

        // Ajuste del AS a 1 si se pasa
        while (total > 21 && ases > 0) {
            total -= 10;
            ases--;
        }

        return total;
    }

    @Override
    public boolean estaEliminado() {
        return calcularPuntuacion() > 21;
    }

    @Override
    public void jugarTurno() {
        // Se implementará en modos concretos
    }

    public String getNombre() {
        return nombre;
    }

    public List<Carta> getMano() {
        return mano;
    }
}