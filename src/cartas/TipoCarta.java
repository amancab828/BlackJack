package cartas;

/**
 * Representa los valores posibles de una carta.
 */
public enum TipoCarta {

    DOS(2),
    TRES(3),
    CUATRO(4),
    CINCO(5),
    SEIS(6),
    SIETE(7),
    OCHO(8),
    NUEVE(9),
    DIEZ(10),
    JOTA(10),
    REINA(10),
    REY(10),
    AS(11);

    private final int valor;

    TipoCarta(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}