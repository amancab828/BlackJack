package interfaces;

import java.util.List;
import cartas.Carta;
import jugadores.AccionJugador;

public interface Jugable {
	String getNombre();
	List<Carta> getMano();
	boolean getEliminado();
	int getVictorias();
	void sumarVictoria();
	void limpiarMano();
	void setEliminado(boolean eliminado);
	void recibirCarta(Carta carta);
	int calcularPuntuacion();
	void mostrarCarta(Carta carta);
	void mostrarCartas();
	AccionJugador decidirAccion();
}
