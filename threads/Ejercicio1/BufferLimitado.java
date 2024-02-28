package threads.Ejercicio1;

public class BufferLimitado {

    private char buffer[];
    private int cont;
    private Object lock = new Object();

    public BufferLimitado(int capacidad) {
        buffer = new char[capacidad];
        this.cont = 0;
    }

    public void poner(char c) {
        synchronized (lock) {
            while (cont == buffer.length) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            buffer[cont] = c;
            cont++;
            lock.notifyAll();
        }
    }

    public char sacar() {
        synchronized (lock) {
            char c = 0;
            try {
                while (cont == 0) {
                    lock.wait();
                }
                c = buffer[cont];
                cont--;
                lock.notifyAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return c;
        }
    }
}
