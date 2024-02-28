package threads.Ejercicio2;

public class CenaFilosofos {

    private static final int NUM_FILOSOFOS = 5;
    private static final Object[] palillos = new Object[NUM_FILOSOFOS];

    public static void main(String[] args) {
        for (int i = 0; i < NUM_FILOSOFOS; i++) {
            palillos[i] = new Object();
        }

        for (int i = 0; i < NUM_FILOSOFOS; i++) {
            new Thread(new Filosofo(i, palillos)).start();
        }
    }
}
