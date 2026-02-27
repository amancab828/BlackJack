package main;

import java.util.Scanner;

/**
 * Utilidades de entrada/salida por consola.
 */
public class Consola {

    private static final Scanner sc = new Scanner(System.in);

    public static String leerString(String msg) {
    	String resultado;
        System.out.print(msg);
        
        resultado = sc.nextLine();
        sc.nextLine(); // Limpiar el buffer
        
        return resultado;
    }

    public static int leerInt(String msg) {
    	int resultado;
        System.out.print(msg);
        
        resultado = sc.nextInt();
        sc.nextLine(); // Limpiar el buffer
        
        return resultado;
    }
}