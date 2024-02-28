package PSP01.Ejercicio2;

import java.io.File;
import java.io.RandomAccessFile;
import java.io.PrintStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

public class Colaborar {

    public static void main(String[] args) {
        Process nuevoProceso;
        String nombreFichero;
        File archivo = null;
        RandomAccessFile raf = null;

        String osName = System.getProperty("os.name");
        if (osName.toUpperCase().contains("WIN")) { //Windows
            if (args.length > 0) {
                nombreFichero = args[0].replace("\\", "\\\\");
            } else {
                nombreFichero = "C:\\Users\\rique\\Documents\\GitHub\\DI\\PSP\\src\\PSP01\\Ejercicio2\\valor.txt";
            }
        } else {
            if (args.length > 0) {
                nombreFichero = args[0];
            } else {
                nombreFichero = "/home/margye/valor.txt";
            }
        }
        try {
            //Redirigimos salida estándar y de error a un fichero
            PrintStream ps = new PrintStream(
                    new BufferedOutputStream(new FileOutputStream(
                            new File("javalog.txt"), true)), true);
            System.setOut(ps);
            System.setErr(ps);
        } catch (Exception e) {
            System.err.println("Error al redirigir las salidas");
            System.err.println(e.toString());
        }

        archivo = new File(nombreFichero);
        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
                raf = new RandomAccessFile(archivo, "rw");
                raf.writeInt(0);
                System.out.println("Creado el fichero.");
            } catch (Exception e) {
                System.err.println("Error al crear el fichero");
                System.err.println(e.toString());
            } finally {
                try {
                    if (null != raf) {
                        raf.close();
                    }
                } catch (Exception e2) {
                    System.err.println("Error al cerrar el fichero");
                    System.err.println(e2.toString());
                    System.exit(1); //Si hay error, finalizamos
                }
            }
        }
        //Creamos un grupo de procesos que accederán al mismo fichero
        try {
            int j = 10;
            for (int i = 0; i <= 10; i++) {
                nuevoProceso = Runtime.getRuntime().exec("java " + "Lenguaje.java " + j + " " + nombreFichero);
                System.out.println("Creado el proceso " + i);
                j += 10;
            }
        } catch (SecurityException ex) {
            System.err.println("Ha ocurrido un error de Seguridad."
                    + "No se ha podido crear el proceso por falta de permisos.");
        } catch (Exception ex) {
            System.err.println("Ha ocurrido un error, descripción: " + ex.toString());
        }
    }
}
