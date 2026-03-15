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
    
    /**
     * Devuelve el valor de la carta como cadena.
     *
     * @return cadena representando la carta
     */
    public String getValorString() {
        return switch (this) {
            case AS -> "A";
            case JOTA -> "J";
            case REINA -> "Q";
            case REY -> "K";
            default -> String.valueOf(valor); // Para cartas del 2 al 10
        };
    }
}