package interfaces;

public interface IConsola {
	String leerString(String msg);
	int leerInt(String msg);
	int leerIntRango(String msg, int minimo, int maximo);
	char readChar(String msg);
	boolean readBooleanUsingChar(String msg, char affirmativeValue, char negativeValue);
}
