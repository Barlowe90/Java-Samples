package threads.Ejercicio1;

public class Productor extends Thread {

    private BufferLimitado buffer;

    public Productor(BufferLimitado buffer) {
        this.buffer = buffer;
    }

    public void run() {
        for (int i = 0; i < 15; i++) {
            char c = (char) ('A' + i);
            buffer.poner(c);
            System.out.println("Productor: " + c);
        }
    }
}
