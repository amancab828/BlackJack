package jugadores;

import cartas.Baraja;

/**
 * Jugador controlado por IA.
 */
public class IAJugador extends Jugador{

    public IAJugador(Baraja baraja) {
        super("IA", baraja);
    }

    /**
     * Decide automáticamente la acción de la IA durante su turno.
     * <p>
     *   La IA sigue una estrategia simple: pide carta si su puntuación
     *   es menor que 18, y se planta si es 18 o más.
     * </p>
     * @return la acción que realizará la IA (PEDIR o PLANTARSE)
     */
    public AccionJugador decidirAccion() {
        int puntos = calcularPuntuacion();

        if (puntos < 18) {
            return AccionJugador.PEDIR;
        } else {
            return AccionJugador.PLANTARSE;
        }
    }
}