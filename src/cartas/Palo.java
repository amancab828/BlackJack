package cartas;

public enum Palo {
    PICAS("♠"), CORAZONES("♥"), DIAMANTES("♦"), TREBOLES("♣");

    private final String simbolo;

    Palo(String simbolo) {
        this.simbolo = simbolo;
    }

    public String getSimbolo() {
        return simbolo;
    }
}