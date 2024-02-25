package PSP03.ejemploTCP;

import java.io.*;
import java.net.*;

public class SocketCliente {

  static final String HOST = "127.0.0.1";
  static final int PUERTO = 2000;

  public SocketCliente() {
    try {

      Socket scliente = new Socket(HOST, PUERTO);
      InputStream aux = scliente.getInputStream();
      DataInputStream flujo_entrada = new DataInputStream(aux);
      System.out.println("Flujo entrada: " + flujo_entrada.readUTF());
      scliente.close();

    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    }

  }

  public static void main(String[] args) {
    for (int i = 0; i < 100; i++)
      new SocketCliente();
  }

}
