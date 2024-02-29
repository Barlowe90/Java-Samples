package threads.crearHilos;

public class Hilo_Runnable implements Runnable {
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("  Hilo_Runnable");
        }
    }
}