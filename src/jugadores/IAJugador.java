package jugadores;

import cartas.Baraja;

/**
 * Jugador controlado por IA.
 */
public class IAJugador extends Jugador {

    public IAJugador(String nombre, Baraja baraja) {
        super(nombre, baraja);
    }

    public AccionJugador decidirAccion() {
        int puntos = calcularPuntuacion();

        if (puntos < 17) {
            return AccionJugador.PEDIR; // pide carta si tiene menos de 17
        } else {
            return AccionJugador.PLANTARSE; // se planta si tiene 17 o más
        }
    }
}