package ejemplos;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

public class FirmaDigital {

    public static void main(String[] args) {
        String texto = "texto de prueba para ser firmado";
        KeyPair clave = FirmaDigital.generarClaves();
        byte[] textoFirmado = FirmaDigital.hacerFirma(texto.getBytes(),
                clave.getPrivate());
        if (FirmaDigital.verificarFirma(texto.getBytes(), clave.getPublic(), textoFirmado)) {
            System.out.println("Firma realizada y verificada correctamente");
        } else {
            System.out.println("Firma incorrecta");
        }

    }

    public static KeyPair generarClaves() {
        KeyPair claves = null;
        try {
            KeyPairGenerator generador = KeyPairGenerator.getInstance("DSA");
            generador.initialize(512);
            claves = generador.genKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return claves;
    }

    /**
     * método que realiza la firma digital del texto o datos y la devuelve
     */
    public static byte[] hacerFirma(byte[] datos, PrivateKey clave) {
        byte[] firmado = null;
        try {
            //crea el objeto tipo Signature con algoritmo DSA
            Signature firma = Signature.getInstance("DSA");
            //inicializa la firma con la clave privada a utilizar
            firma.initSign(clave);
            //obtine el resumen del mensaje
            firma.update(datos);
            //obtien la firma digital
            firmado = firma.sign();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return firmado;
    }

    /**
     * Método que verifica la firma digital, devolviendo: false, si la firma no
     * es correcta o se produce una excepción y verdadero, si la firma es
     * correcta.
     */
    public static boolean verificarFirma(byte[] texto, PublicKey clave,
            byte[] textoFirmado) {
        try {
            //crea el objeto tipo Signature con algoritmo DSA
            Signature firma = Signature.getInstance("DSA");
            //verifica la clave pública
            firma.initVerify(clave);
            //actualiza el resumen de mensaje
            firma.update(texto);
            //devuelve el resultado de la verificación
            return (firma.verify(textoFirmado));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
