package jugadores;

import cartas.Baraja;
import cartas.Carta;
import cartas.TipoCarta;
import interfaces.Jugable;
import main.Consola;
import java.util.*;

/**
 * Representa a un jugador de Blackjack.
 */
public class Jugador implements Jugable {

    protected String nombre;
    protected List<Carta> mano = new ArrayList<>();
    protected boolean eliminado = false;
    protected boolean plantado = false;
    protected Baraja baraja; 
    Consola consola = new Consola();

    //Constructor
    public Jugador(String nombre, Baraja baraja) {
        this.nombre = nombre;
        this.baraja = baraja;
    }

    //Getters
    public String getNombre() {
        return nombre;
    }
	public Baraja getBaraja() {
		return baraja;
	}
    public List<Carta> getMano() {
        return mano;
    }
    public boolean getEliminado() {
		return eliminado;
	}
    
    /**
     * Añade una carta a la mano.
     * @param carta carta robada
     */
    public void recibirCarta(Carta carta) {
        mano.add(carta);
        
        if (calcularPuntuacion() > 21) {
            eliminado = true;
        }
    }

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
    
    public void mostrarCarta(Carta carta) {
        String valor = carta.getValorString(); 
        String palo = carta.getSimboloPalo();  

        System.out.println("┌─────────┐");
        System.out.printf("|%-2s       |\n", valor);
        System.out.println("|         |");
        System.out.printf("|    %s    |\n", palo);
        System.out.println("|         |");
        System.out.printf("|       %2s|\n", valor);
        System.out.println("└─────────┘");
    }
    
    public void mostrarCartas() {
        System.out.println("Cartas de " + nombre + ":");
        for (Carta c : mano) {
            mostrarCarta(c);
        }
        System.out.println("Puntos: " + calcularPuntuacion() + "\n");
    }

    public AccionJugador decidirAccion() {
    	AccionJugador accion = null;
        System.out.println("¿Qué quieres hacer?");
        System.out.println("1. Pedir carta");
        System.out.println("2. Plantarse");

        int opcion = consola.leerIntRango("Elige una opción: ", 1, 2);

        switch (opcion) {
            case 1 -> accion = AccionJugador.PEDIR;
            case 2 -> accion = AccionJugador.PLANTARSE;
        };
        
        return accion;
    }
}