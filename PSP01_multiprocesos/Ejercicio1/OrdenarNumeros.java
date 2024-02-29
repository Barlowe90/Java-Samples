package multi_process.Ejercicio1;

import java.net.*;
import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Aplicación que ordena un conjunto indeterminado de números que recibe a
 * través de su entrada estándar; y muestra el resultado de la ordenación en su
 * salida estándar
 *
 * @author adri
 */
public class OrdenarNumeros {

    public static void main(String[] args) {
        Socket canal = null;
        BufferedReader entrada = null;

        List<Integer> listaOrdenada = new LinkedList<>();

        try {
            canal = new Socket("localhost", 12345);
        } catch (Exception ex) {
            System.err.println("No se ha podido establecer conexión.");
            System.err.println(ex.toString());
        }
        if (canal != null) {
            try {
                entrada = new BufferedReader(new InputStreamReader(canal.getInputStream()));
                String linea;
                while ((linea = entrada.readLine()) != null) {
                    try {
                        int valorEntero = Integer.parseInt(linea.trim());
                        listaOrdenada.add(valorEntero);
                    } catch (NumberFormatException e) {
                        System.err.println("Error al convertir la línea a un entero: " + linea);
                        e.printStackTrace();
                    }
                }
                System.out.println("Numeros ordenados:");
                listaOrdenada.stream().sorted().forEach(n -> System.out.println(n));
            } catch (Exception ex) {
                System.err.println("No se ha podido establecer conexión.");
                System.err.println(ex.toString());
            } finally {
                if (entrada != null) {
                    try {
                        entrada.close();
                    } catch (IOException ex) {
                        System.err.println("Se ha producido un error al cerrar el InputStreamReader.");
                        System.err.println(ex.toString());
                    }
                    if (canal != null) {
                        try {
                            canal.close();
                        } catch (IOException ex) {
                            System.err.println("Se ha producido un error al cerrar el Socket.");
                            System.err.println(ex.toString());
                        }
                    }
                }
            }
        }
    }
}
