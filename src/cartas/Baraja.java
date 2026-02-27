package cartas;

import java.util.*;

/**
 * Representa una baraja de cartas.
 */
public class Baraja {

    private List<Carta> cartas;

    /**
     * Constructor que crea la baraja completa.
     */
    public Baraja() {
        cartas = new ArrayList<>();
        inicializar();
        barajar();
    }

    /**
     * Genera todas las cartas de la baraja.
     */
    private void inicializar() {
        for (Palo p : Palo.values()) {
            for (TipoCarta t : TipoCarta.values()) {
                cartas.add(new Carta(p, t));
            }
        }
    }

    /**
     * Mezcla la baraja.
     */
    public void barajar() {
        Collections.shuffle(cartas);
    }

    /**
     * Roba una carta de la baraja.
     * 
     * @return carta robada o null si no hay
     */
    public Carta robarCarta() {
        if (cartas.isEmpty()) return null;
        return cartas.remove(0);
    }

    /**
     * Indica si quedan cartas.
     * 
     * @return true si quedan cartas
     */
    public boolean quedanCartas() {
        return !cartas.isEmpty();
    }
}