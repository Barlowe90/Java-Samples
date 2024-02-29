package threads.crearHilos;

public class Main {

    public static void main(String[] args) {
        Thread hilo1 = new Hilo_Thread("Isabel");
        Thread hilo2 = new Hilo_Thread();

        Thread hilo3 = new Thread(new Hilo_Runnable());

        hilo1.start();
        hilo2.start();
        hilo3.start();
    }

}
