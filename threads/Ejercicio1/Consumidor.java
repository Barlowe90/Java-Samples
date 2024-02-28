package threads.Ejercicio1;

public class Consumidor extends Thread {

    private BufferLimitado buffer;

    public Consumidor(BufferLimitado buffer) {
        this.buffer = buffer;
    }

    public void run() {
        for (int i = 0; i < 15; i++) {
            char c = buffer.sacar();
            System.out.println("Consumidor: " + c);

        }
    }
}
