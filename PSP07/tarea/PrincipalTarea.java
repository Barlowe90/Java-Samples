package PSP07.tarea;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class PrincipalTarea {
    public static void main(String[] args) {
        String nombreUsuario = "usuario"; // Nombre de usuario
        String password = "password"; // Contraseña

        // Concatenar nombre de usuario y contraseña para generar la semilla
        String semilla = nombreUsuario + password;

        try {
            // Generar la clave a partir de la semilla
            SecretKeySpec clave = generarClave(semilla);

            // Generar cadena de texto a encriptar
            String cadena = "Hola Mundo!";

            // Encriptar cadena y escribirla en un fichero
            encriptarYGuardar(cadena, clave, "fichero.cifrado");
            System.out.println("Cadena encriptada y guardada en fichero: fichero.cifrado");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para generar la clave a partir de la semilla
    private static SecretKeySpec generarClave(String semilla) throws NoSuchAlgorithmException {
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(semilla.getBytes());
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128, random);
        SecretKey claveSecreta = keyGen.generateKey();
        return new SecretKeySpec(claveSecreta.getEncoded(), "AES");
    }

    // Método para encriptar una cadena y guardarla en un fichero
    private static void encriptarYGuardar(String cadena, SecretKeySpec clave, String nombreFichero) throws Exception {
        Cipher cifrador = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cifrador.init(Cipher.ENCRYPT_MODE, clave);
        byte[] cadenaEncriptada = cifrador.doFinal(cadena.getBytes());
        try (OutputStream fos = new FileOutputStream(nombreFichero)) {
            fos.write(cadenaEncriptada);
        }
    }
}