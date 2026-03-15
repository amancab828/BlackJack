package cartas;

/**
 * Representa una carta de la baraja.
 */
public class Carta {

    private Palo palo;
    private TipoCarta tipo;

    // Constructor
    public Carta(Palo palo, TipoCarta tipo) {
        this.palo = palo;
        this.tipo = tipo;
    }

    // Getters
    public int getValor() {
        return tipo.getValor();
    }
    public Palo getPalo() {
        return palo;
    }
    public TipoCarta getTipo() {
        return tipo;
    }
}