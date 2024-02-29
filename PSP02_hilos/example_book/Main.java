package threads.example_book;

public class Main {
    public static void main(String[] args) {
        AlmacenCuadros almacen = new AlmacenCuadros();

        Painter p = new Painter(almacen);
        Seller s = new Seller(almacen);

        p.start();
        s.start();
    }
}
