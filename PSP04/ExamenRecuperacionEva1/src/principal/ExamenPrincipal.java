package principal;

public class ExamenPrincipal {

    public static void main(String[] args) {
        ArrayLimitado array = new ArrayLimitado();
        Productor p = new Productor(array);
        Consumidor c1 = new Consumidor(array, "con1");
        Consumidor c2 = new Consumidor(array, "con2");
        p.start();
        c1.start();
        c2.start();

        try {
            p.join();
            c1.join();
            c2.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
