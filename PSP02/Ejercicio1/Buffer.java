package PSP02.Ejercicio1;

public class Buffer {

    public static void main(String[] args) {
        BufferLimitado buffer = new BufferLimitado(6);
        Productor p = new Productor(buffer);
        Consumidor c = new Consumidor(buffer);
        p.start();
        c.start();
        try {
            p.join();
            c.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
