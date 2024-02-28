package threads.Ejercicio1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BufferLimitado {

    private char buffer[];
    private int ocupados, cola, frente;
    private Lock lock = new ReentrantLock();
    private Condition productor = lock.newCondition();
    private Condition consumidor = lock.newCondition();

    public BufferLimitado(int capacidad) {
        buffer = new char[capacidad];
        this.ocupados = this.cola = this.frente = 0;
    }

    public void poner(char c) {
        lock.lock();
        try {
            while (ocupados >= buffer.length) {
                productor.await();
            }
            buffer[frente] = c;
            cola = frente;
            frente = (frente + 1) % buffer.length;
            ocupados++;
            consumidor.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public char sacar() {
        lock.lock();
        char c = 0;
        try {
            while (ocupados <= 0) {
                consumidor.await();
            }
            c = buffer[cola];
            ocupados--;
            cola = (cola - 1 + buffer.length) % buffer.length;
            productor.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return c;
    }
}
