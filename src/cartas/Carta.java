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

    // Método para mostrar A, J, Q, K en lugar de 1, 11, 12, 13
    public String getValorString() {
        return switch (tipo) {
            case AS -> "A";
            case JOTA -> "J";
            case REINA -> "Q";
            case REY -> "K";
            default -> String.valueOf(tipo.getValor());
        };
    }

    public String getSimboloPalo() {
        return palo.getSimbolo();
    }
}