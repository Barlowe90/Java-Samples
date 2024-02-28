package PSP03.ejemploUDP;

import java.net.*;
import java.io.*;

public class ReceptorUDP {

  public static void main(String[] args) {

    if (args.length != 0) {
      System.err.println("Uso: java ReceptorUDP");
    } else
      try {
        DatagramSocket sSocket = new DatagramSocket(1559);

        byte[] cadena = new byte[1000];
        DatagramPacket mensaje = new DatagramPacket(cadena, cadena.length);

        System.out.println("Esperando mensajes..");
        while (true) {
          sSocket.receive(mensaje);
          String datos = new String(mensaje.getData(), 0, mensaje.getLength());
          System.out.println("\tMensaje Recibido: " + datos);
        }
      } catch (SocketException e) {
        System.err.println("Socket: " + e.getMessage());
      } catch (IOException e) {
        System.err.println("E/S: " + e.getMessage());
      }
  }
}
