package main;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Utilidades de entrada/salida por consola.
 */
public class Consola {

    private static final Scanner sc = new Scanner(System.in);

    // Limpiar el teclado
    private void cleanInput() {
        sc.nextLine();
    }
    
	// Retorna una cadena de caracteres introducida por el usuario
	public String leerString(String msg) {
		System.out.print(msg);
		return sc.nextLine();
	}

	// Retorna un int introducido por el usuario
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
    
	// Retorna un int introducido por el usuario cuyo valor esté en el rango [minimo, maximo], ambos incluidos
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
}