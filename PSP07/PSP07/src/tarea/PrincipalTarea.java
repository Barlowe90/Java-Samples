package tarea;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.SecretKeySpec;

public class PrincipalTarea {

    public static void main(String[] args) {
        String cadena = "Hola Mundo!";
        String fichero = "fichero.cifrado";
        String usuario = "user";
        String password = "pw";
        String semilla = usuario + password;

        PrincipalTarea main = new PrincipalTarea();

        try {
            SecretKeySpec clave = main.generarClave(semilla); // Genero clave a partir de la semilla
            main.encriptarYguardar(cadena, clave, fichero);
            System.out.println("Cadena encriptada y guardada en el fichero");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(PrincipalTarea.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(PrincipalTarea.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(PrincipalTarea.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(PrincipalTarea.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(PrincipalTarea.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PrincipalTarea.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void encriptarYguardar(String cadena, SecretKeySpec clave, String fichero) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, FileNotFoundException, IOException {
        Cipher cifrador = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cifrador.init(Cipher.ENCRYPT_MODE, clave);
        byte[] cadenaCifrada = cifrador.doFinal(cadena.getBytes());
        try (OutputStream out = new FileOutputStream(fichero)) {
            out.write(cadenaCifrada);
        }
    }

    private SecretKeySpec generarClave(String semilla) throws NoSuchAlgorithmException {
        SecureRandom numRandom = SecureRandom.getInstance("SHA1PRNG");
        numRandom.setSeed(semilla.getBytes());
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128, numRandom);
        SecretKey claveSecreta = keyGen.generateKey();
        return new SecretKeySpec(claveSecreta.getEncoded(), "AES");
    }
}
