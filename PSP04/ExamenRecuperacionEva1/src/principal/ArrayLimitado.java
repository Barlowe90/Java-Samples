package principal;

public class ArrayLimitado {

    private int buffer[];
    private int cont;

    public ArrayLimitado() {
        this.buffer = new int[5];
        this.cont = 0;
    }

    public synchronized void guardar(int num) {
        while (cont == buffer.length) {
            try {
                this.wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        buffer[cont] = num;
        cont++;
        this.notifyAll();
    }

    public synchronized int sacar() {
        int num = 0;
        try {
            while (cont == 0) {
                System.out.println("Buffer vacio");
                this.wait();
            }
            cont--;
            num = buffer[cont];
            this.notifyAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return num;
    }

}
