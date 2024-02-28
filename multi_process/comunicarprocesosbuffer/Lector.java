package multi_process.comunicarprocesosbuffer;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Lector {
    public static void main(String[] args) {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader bf = new BufferedReader(isr);
        String lineaTeclado = null;

        try {
            System.out.println("Proceso lector");
            while ((lineaTeclado = bf.readLine()) != null) {
                System.out.println(lineaTeclado);
            }
        } catch (Exception e) {
            System.err.println("Se ha producido un error de E/S.");
            System.err.println(e.toString());
        }
    }
}
