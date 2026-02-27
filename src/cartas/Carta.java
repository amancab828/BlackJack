package cartas;

import interfaces.Mostrable;

/**
 * Representa una carta de la baraja.
 */
public class Carta implements Mostrable {

    private Palo palo;
    private TipoCarta tipo;

    /**
     * Constructor de carta.
     * @param palo palo de la carta
     * @param tipo tipo/valor de la carta
     */
    public Carta(Palo palo, TipoCarta tipo) {
        this.palo = palo;
        this.tipo = tipo;
    }

    /**
     * Obtiene el valor de la carta.
     * @return valor numérico
     */
    public int getValor() {
        return tipo.getValor();
    }

    public Palo getPalo() {
        return palo;
    }

    public TipoCarta getTipo() {
        return tipo;
    }

    @Override
    public String mostrar() {
        return tipo + " de " + palo;
    }
}