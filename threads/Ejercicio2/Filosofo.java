package PSP02.Ejercicio2;

public class Filosofo implements Runnable {

    private int id;
    private Object izquierdo;
    private Object derecho;

    public Filosofo(int id, Object[] palillos) {
        this.id = id;
        this.izquierdo = palillos[id];
        this.derecho = palillos[(id + 1) % palillos.length];
    }

    private void pensar() throws InterruptedException {
        System.out.println("Filósofo " + id + " está pensando");
        Thread.sleep((long) (Math.random() * 100));
    }

    private void comer() throws InterruptedException {
        synchronized (izquierdo) {
            synchronized (derecho) {
                System.out.println("Filósofo " + id + " está comiendo");
                Thread.sleep((long) (Math.random() * 100));
            }
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                pensar();
                comer();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
