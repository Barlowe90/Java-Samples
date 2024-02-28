package PSP03.ejemploUDP;

import java.net.*;
import java.io.*;

public class EmisorUDP {

  public static void main(String[] args) {

    if (args.length != 2) {
      System.err.println("Uso: java EmisorUDP maquina mensaje");
    } else
      try {

        DatagramSocket sSocket = new DatagramSocket();

        InetAddress maquina = InetAddress.getByName(args[0]);
        int Puerto = 1559;

        byte[] cadena = args[1].getBytes();
        DatagramPacket mensaje = new DatagramPacket(cadena, args[1].length(), maquina, Puerto);

        sSocket.send(mensaje);

        sSocket.close();
      } catch (UnknownHostException e) {
        System.err.println("Desconocido: " + e.getMessage());
      } catch (SocketException e) {
        System.err.println("Socket: " + e.getMessage());
      } catch (IOException e) {
        System.err.println("E/S: " + e.getMessage());
      }
  }
}
