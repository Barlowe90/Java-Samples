package ejemplos;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class CifradoAsimetrico {

    public static void main(String[] args) {
        try {
            // Generamos una pareja de claves
            SecureRandom secureRandom = new SecureRandom();

            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048, secureRandom);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();

            // Encripto con Cipher y la clave pública
            Cipher cifrador = Cipher.getInstance("RSA");
            cifrador.init(Cipher.ENCRYPT_MODE, keyPair.getPublic());

            byte[] datos = "En un lugar de la mancha".getBytes();
            byte[] datosCifrados;

            datosCifrados = cifrador.doFinal(datos);

            // Creo otro cipher y descifro con la clave privada
            Cipher descifrador = Cipher.getInstance("RSA");
            descifrador.init(Cipher.DECRYPT_MODE, keyPair.getPrivate());

            byte[] datosDescifrados;

            datosDescifrados = descifrador.doFinal(datosCifrados);

            System.out.println("Datos descifrados: " + new String(datosDescifrados));

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(CifradoAsimetrico.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(CifradoAsimetrico.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(CifradoAsimetrico.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(CifradoAsimetrico.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(CifradoAsimetrico.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
