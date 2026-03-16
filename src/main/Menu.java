package main;

import juego.ModoJuego;
import juego.Partida;
import juego.ResultadoRonda;
import juego.Ronda;

/**
 * Clase que gestiona el menú principal y la ejecución del juego.
 */
public class Menu {
	
	Consola consola = new Consola();
	
    /**  
     * Selecciona el modo de juego mediante opciones de consola
     */
    private ModoJuego seleccionarModo() {
    	ModoJuego modo = null;
        int opcion;

        System.out.println("===== BLACKJACK =====");
        System.out.println("1. Multijugador");
        System.out.println("2. Contra Croupier");
        System.out.println("3. Contra IA");
        System.out.println("0. Salir");

        opcion = consola.leerIntRango("Elige una opción: ", 0, 3);

        switch (opcion) {
            case 1 -> modo = ModoJuego.MULTIJUGADOR;
            case 2 -> modo = ModoJuego.CROUPIER;
            case 3 -> modo = ModoJuego.IA;
            case 0 -> modo = null; 
        }
            
        return modo;
    }

    /**
     * Inicia el flujo principal del programa, gestionando partidas y rondas.
     * Pregunta al usuario si desea continuar jugando después de cada ronda.
     */
    public void iniciar() {
        ModoJuego modo = seleccionarModo();
        
        // Si no se selecciona modo válido, terminamos de forma controlada
        if (modo != null) {
            Partida partida = new Partida(modo);
            Ronda ronda = new Ronda(partida);
            ResultadoRonda resultado = new ResultadoRonda(partida);

            boolean seguirJugando;
            do {
                ronda.jugar();
                resultado.calcularGanador();

                seguirJugando = consola.readBooleanUsingChar("\n¿Quieres jugar otra ronda? S/N: ", 'S', 'N');

                if (seguirJugando) {
                    ronda.reiniciar();
                }

            } while (seguirJugando);
        }

        System.out.println("Gracias por jugar. ¡Hasta la próxima!");
    }
}