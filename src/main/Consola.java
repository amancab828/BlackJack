package main;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Utilidades de entrada/salida por consola.
 */
public class Consola implements interfaces.IConsola {

    private static final Scanner sc = new Scanner(System.in);

    // Limpiar el teclado
    private void cleanInput() {
        sc.nextLine();
    }
    
    /**
     * Lee una cadena de caracteres introducida por el usuario.
     *
     * @param msg mensaje que se muestra antes de leer
     * @return cadena introducida por el usuario
     */
    @Override
	public String leerString(String msg) {
		System.out.print(msg);
		return sc.nextLine();
	}

    /**
     * Lee un número entero introducido por el usuario.
     * Reintenta hasta que se ingrese un valor válido.
     *
     * @param msg mensaje que se muestra antes de leer
     * @return número entero introducido por el usuario
     */
    @Override
	public int leerInt(String msg) {
		System.out.print(msg);
		int value = 0;
		boolean error;
	
		do {
			try {
				value = sc.nextInt();
				error = false;
			} catch (InputMismatchException e) {
				System.err.printf("¡Error! Eso no es un número entero\n");
				error = true;
			} finally {
				cleanInput();
			}
		} while (error);
	
		return value;
	}
    
    /**
     * Lee un número entero introducido por el usuario que esté dentro de un rango dado.
     * Reintenta hasta que el valor ingresado esté entre {@code minimo} y {@code maximo} inclusive.
     *
     * @param msg mensaje que se muestra antes de leer
     * @param minimo valor mínimo permitido
     * @param maximo valor máximo permitido
     * @return número entero dentro del rango
     */
    @Override
	public int leerIntRango(String msg, int minimo, int maximo) {
		System.out.print(msg);
		int value;
		do {
			value = leerInt("");
			if (value < minimo || value > maximo) {
				System.err.printf("¡Error! El número debe estar entre %d y %d\n", minimo, maximo);
			}
		} while (value < minimo || value > maximo);
		return value;
	}
	
    /**
     * Lee un único carácter introducido por el usuario.
     * Reintenta si se ingresa más de un carácter.
     *
     * @param msg mensaje que se muestra antes de leer
     * @return carácter introducido por el usuario
     */
    @Override
	public char readChar(String msg) {
		System.out.print(msg);
		while (true) {
			String input = sc.nextLine();
			if (input.length() == 1) {
				return input.charAt(0);
			} else {
				System.err.println("¡Error! Debes introducir solo un carácter.");
			}
		}
	}
	
    /**
     * Lee un valor booleano a partir de un carácter introducido por el usuario.
     * Retorna {@code true} si coincide con {@code affirmativeValue} y {@code false} si coincide con {@code negativeValue}.
     * Ignora mayúsculas/minúsculas.
     *
     * @param msg mensaje que se muestra antes de leer
     * @param affirmativeValue carácter que representa "sí"
     * @param negativeValue carácter que representa "no"
     * @return {@code true} si se introduce {@code affirmativeValue}, {@code false} si se introduce {@code negativeValue}
     */
    @Override
	public boolean readBooleanUsingChar(String msg, char affirmativeValue, char negativeValue) {
		System.out.printf("%s (\"%c\" para sí o \"%c\" para no): ", msg, affirmativeValue, negativeValue);
		while (true) {
			char c = readChar("");
			if (Character.toLowerCase(c) == Character.toLowerCase(affirmativeValue)) {
				return true;
			} else if (Character.toLowerCase(c) == Character.toLowerCase(negativeValue)) {
				return false;
			} else {
				System.err.printf("¡Error! Introduce un carácter válido.\n\"%c\" para sí o \"%c\" para no: ", affirmativeValue, negativeValue);
			}
		}
	}
}