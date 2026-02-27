package main;

import juego.ModoJuego;

/**
 * Gestiona el menú principal del Blackjack.
 */
public class Menu {

    /**
     * Muestra el menú principal y devuelve el modo elegido.
     * @return modo de juego seleccionado
     */
    public ModoJuego seleccionarModo() {
        int opcion;

        do {
            System.out.println("\n===== BLACKJACK =====");
            System.out.println("1. Multijugador");
            System.out.println("2. Contra Croupier");
            System.out.println("3. Contra IA");
            System.out.println("0. Salir");

            opcion = Consola.leerInt("Elige una opción: ");

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

        if (modo == null) {
            System.out.println("Saliendo del juego...");
            return;
        }

        juego.Partida partida = new juego.Partida(modo);
        partida.jugar();
    }
}