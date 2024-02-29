package PSP06;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.*;

public class Ejercicio1 {

    Pattern pat = null;
    Matcher mat = null;

    public Ejercicio1() {
        loginUsuario();
        solicitudFichero();
    }

    public static void main(String[] args) {
        new Ejercicio1();
    }

    private void solicitudFichero() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el nombre del fichero (maximo 8 caracteres y extension de 3 caracteres): ");
        String nombreFichero = sc.nextLine();
        pat = Pattern.compile("[a-zA-Z0-9]{1,8}\\.[a-zA-Z0-9]{3}");
        mat = pat.matcher(nombreFichero);
        while (!mat.find()) {
            System.out.println("El nombre del fichero no cumple con el formato, intente de nuevo: ");
            nombreFichero = sc.nextLine();
            mat = pat.matcher(nombreFichero);
        }
        // Una vez que el nombre del fichero cumple con el formato, mostrar por salida
        // estándar el contenido del fichero
        System.out.println("El contenido del fichero es: ");
        mostrarContenido(nombreFichero);
        sc.close();
    }

    private void loginUsuario() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce tu nombre de usuario (8 caracteres en minusculas): ");
        String nombreUsuario = sc.nextLine();
        pat = Pattern.compile("[a-z]{8}");
        mat = pat.matcher(nombreUsuario);
        while (!mat.find()) {
            System.out.println("El nombre de usuario no cumple con el formato, intente de nuevo: ");
            nombreUsuario = sc.nextLine();
            mat = pat.matcher(nombreUsuario);
        }
    }

    private static void mostrarContenido(String nombreFichero) {
        try (BufferedReader br = new BufferedReader(new FileReader(nombreFichero))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}