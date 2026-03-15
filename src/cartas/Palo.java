package cartas;

/**
 * Representa los palos posibles de una carta.
 */
public enum Palo {
    PICAS("♠"), CORAZONES("♥"), DIAMANTES("♦"), TREBOLES("♣");

    private final String simbolo;

    // Constructor del enum
    Palo(String simbolo) {
        this.simbolo = simbolo;
    }

    public String getSimbolo() {
        return simbolo;
    }
}