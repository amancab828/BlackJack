package jugadores;

import java.util.ArrayList;
import java.util.List;

import cartas.Baraja;
import cartas.Carta;
import cartas.Palo;
import cartas.TipoCarta;
import interfaces.Jugable;
import main.Consola;

/**
 * Representa a un jugador de Blackjack.
 */
public class Jugador implements Jugable {

    protected String nombre;
    protected Baraja baraja;
    protected List<Carta> mano = new ArrayList<>();
    protected boolean eliminado = false;
    protected boolean plantado = false;
    protected int victorias = 0;
    Consola consola = new Consola();

    //Constructor
    public Jugador(String nombre, Baraja baraja) {
        this.nombre = nombre;
        this.baraja = baraja;
    }

    /** Retorna el nombre del jugador*/
    @Override
    public String getNombre() {
        return nombre;
    }
    /** Retorna la lista de cartas que tiene el jugador en mano*/
    @Override
    public List<Carta> getMano() {
        return mano;
    }
    /** Indica si el jugador está eliminado (se ha pasado de 21)*/
    @Override
    public boolean getEliminado() {
        return eliminado;
    }
    /** Retorna el número de victorias acumuladas del jugador*/
    @Override
    public int getVictorias() {
        return victorias;
    }
    /** Retorna el número de victorias acumuladas del jugador*/
    @Override
    public boolean getPlantado() {
        return plantado;
    }
    /** Suma 1 a las victorias del jugador*/
    @Override
    public void sumarVictoria() {
        victorias++;
    }
    /** Limpia la mano del jugador y lo marca como no plantado*/
    @Override
    public void limpiarMano() {
        mano.clear();
        plantado = false;
    }
    /**
     * Establece si el jugador está eliminado o no.
     * @param eliminado true si el jugador se ha pasado, false si sigue en juego
     */
    @Override
    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }
    /**
     * Establece si el jugador está eliminado o no.
     * @param eliminado true si el jugador se ha pasado, false si sigue en juego
     */
    @Override
    public void setPlantado(boolean plantado) {
        this.plantado = plantado;
    }
    
    /**
     * Añade una carta a la mano.
     * @param carta carta robada
     */
    @Override
    public void recibirCarta(Carta carta) {
        mano.add(carta);
        if (calcularPuntuacion() > 21) {
            eliminado = true;
        }
    }

    /**
     * Calcula la puntuación total del jugador según sus cartas.
     * Los Ases pueden contar como 11 o 1 para evitar pasarse de 21.
     *
     * @return total de puntos de la mano
     */
    @Override
    public int calcularPuntuacion() {
        int total = 0;
        int numAses = 0;

        for (Carta c : mano) {
            if (c.getTipo() == TipoCarta.AS) {
                numAses++;
                total += 11;
            } else {
                total += c.getValor();
            }
        }

        // Mientras estemos >21 y tengamos Ases contados como 11, los reducimos a 1
        for (int i = 0; i < numAses; i++) {
            if (total > 21) {
                total -= 10;
            }
        }

        return total;
    }
    
    /**
     * Muestra una carta por consola en formato visual.
     *
     * @param carta carta a mostrar
     */
    @Override
    public void mostrarCarta(Carta carta) {
    	Palo tipoPalo = carta.getPalo();
    	TipoCarta tipoCarta = carta.getTipo();
        String valor = tipoCarta.getValorString(); 
        String palo = tipoPalo.getSimbolo();  

        System.out.println("┌─────────┐");
        System.out.printf("|%-2s       |\n", valor);
        System.out.println("|         |");
        System.out.printf("|    %s    |\n", palo);
        System.out.println("|         |");
        System.out.printf("|       %2s|\n", valor);
        System.out.println("└─────────┘");
    }
    
    /**
     * Muestra todas las cartas de la mano del jugador y su puntuación actual.
     */
    @Override
    public void mostrarCartas() {
        System.out.println("Cartas de " + nombre + ":");
        for (Carta c : mano) {
            mostrarCarta(c);
        }
        System.out.println("Puntos: " + calcularPuntuacion() + "\n");
    }
    
    /**
     * Decide la acción del jugador durante su turno.
     * Para jugadores humanos, muestra un menú y recibe la elección.
     *
     * @return acción elegida (PEDIR o PLANTARSE)
     */
    @Override
    public AccionJugador decidirAccion() {
    	AccionJugador accion = null;
    	int opcion;
    	System.out.println("\nTurno de " + nombre + " (Puntos: " + calcularPuntuacion() + ")");
        System.out.println("¿Qué quieres hacer?");
        System.out.println("1. Pedir carta");
        System.out.println("2. Plantarse");

        opcion = consola.leerIntRango("Elige una opción: ", 1, 2);

        switch (opcion) {
            case 1 -> accion = AccionJugador.PEDIR;
            case 2 -> accion = AccionJugador.PLANTARSE;
        };
        
        return accion;
    }
}