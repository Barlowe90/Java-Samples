package PSP06;

import java.util.regex.*;

public class EjemploExpresionRegular {

    public static void main(String[] args) {

        String supuestoDNI = "34811755-N";

        Pattern pat = null;
        Matcher mat = null;
        pat = Pattern.compile("[0-9]{8}-[a-zA-Z]");

        mat = pat.matcher(supuestoDNI);

        if (mat.find()) {
            System.out.println("DNI correcto");
        } else {
            System.out.println("DNI INcorrecto");
        }

    }

}
