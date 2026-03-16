package cartas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Representa una baraja de cartas.
 */
public class Baraja {

    private List<Carta> cartas;

    // Crea una baraja nueva y la baraja automáticamente.
    public Baraja() {
        cartas = new ArrayList<>();
        inicializar();
        barajar();
    }

    // Llena la baraja con las 52 cartas.
    private void inicializar() {
        for (Palo p : Palo.values()) {
            for (TipoCarta t : TipoCarta.values()) {
                cartas.add(new Carta(p, t));
            }
        }
    }
    // Baraja las cartas usando Collections.shuffle
    private void barajar() {
        Collections.shuffle(cartas);
    }
    // Verifica si quedan cartas en la baraja
    private boolean quedanCartas() {
        return !cartas.isEmpty();
    }
    
    /**
     * Roba la primera carta de la baraja.
     * <p>
     * Si la baraja está vacía, se reinicia automáticamente con las 52 cartas y se baraja antes de robar.
     * Esto garantiza que nunca se devuelva {@code null}.
     * </p>
     * @return la carta robada de la baraja
     */
    public Carta robarCarta() {
        // Si la baraja está vacía, reiniciamos y barajamos
        if (!quedanCartas()) {
            System.out.println("La baraja se ha acabado. Barajamos");
            inicializar();
            barajar();
        }

        // Ahora sí robamos la primera carta
        return cartas.remove(0);
    }
}