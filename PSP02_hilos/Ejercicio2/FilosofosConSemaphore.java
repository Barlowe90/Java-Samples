package threads.Ejercicio2;

import java.util.concurrent.Semaphore;

public class FilosofosConSemaphore {
    private static final int NUM_FILOSOFOS = 5;
    private static Semaphore[] tenedores = new Semaphore[NUM_FILOSOFOS];
    private static Semaphore mutex = new Semaphore(1); // Mutex para garantizar la exclusión mutua

    public static void main(String[] args) {
        for (int i = 0; i < NUM_FILOSOFOS; i++) {
            tenedores[i] = new Semaphore(1); // Cada tenedor se inicializa como disponible
        }

        Thread[] filosofos = new Thread[NUM_FILOSOFOS];
        for (int i = 0; i < NUM_FILOSOFOS; i++) {
            final int idFilosofo = i;
            filosofos[i] = new Thread(() -> {
                try {
                    while (true) {
                        pensar(idFilosofo);
                        tomarTenedores(idFilosofo);
                        comer(idFilosofo);
                        dejarTenedores(idFilosofo);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            filosofos[i].start();
        }
    }

    private static void pensar(int idFilosofo) throws InterruptedException {
        System.out.println("El filósofo " + idFilosofo + " está pensando.");
        Thread.sleep((long) (Math.random() * 1000));
    }

    private static void tomarTenedores(int idFilosofo) throws InterruptedException {
        mutex.acquire(); // Entrar en la región crítica
        tenedores[idFilosofo].acquire(); // Tomar el tenedor izquierdo
        tenedores[(idFilosofo + 1) % NUM_FILOSOFOS].acquire(); // Tomar el tenedor derecho
        mutex.release(); // Salir de la región crítica
    }

    private static void comer(int idFilosofo) throws InterruptedException {
        System.out.println("El filósofo " + idFilosofo + " está comiendo.");
        Thread.sleep((long) (Math.random() * 1000));
    }

    private static void dejarTenedores(int idFilosofo) {
        tenedores[idFilosofo].release(); // Dejar el tenedor izquierdo
        tenedores[(idFilosofo + 1) % NUM_FILOSOFOS].release(); // Dejar el tenedor derecho
    }
}