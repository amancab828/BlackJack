package main;

import juego.ModoJuego;
import juego.Partida;
import juego.Ronda;


public class Menu {
	
	Consola consola = new Consola();
	
    public ModoJuego seleccionarModo() {
        int opcion;

        do {
            System.out.println("===== BLACKJACK =====");
            System.out.println("1. Multijugador");
            System.out.println("2. Contra Croupier");
            System.out.println("3. Contra IA");
            System.out.println("0. Salir");

            opcion = consola.leerInt("Elige una opción: ");

            switch (opcion) {
                case 1:
                    return ModoJuego.MULTIJUGADOR;
                case 2:
                    return ModoJuego.CROUPIER;
                case 3:
                    return ModoJuego.IA;
                case 0:
                    return null;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (true);
    }

    /**
     * Inicia el flujo principal del programa.
     */
    public void iniciar() {
    	ModoJuego modo = seleccionarModo();
    	Partida partida = new Partida(modo);
        Ronda ronda = new Ronda(partida);
    	ronda.jugar();
    }
}