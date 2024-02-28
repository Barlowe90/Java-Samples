package PSP05;

import java.io.IOException;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;

public class ReadAllServerHeaderFieldsProjectSinMaven {

    public static void main(String[] args) {
        try {
            String url = "https://google.com";
            URL urlObj = null;
            try {
                urlObj = new URL(url);
            } catch (MalformedURLException ex) {
                Logger.getLogger(ReadAllServerHeaderFieldsProjectSinMaven.class.getName()).log(Level.SEVERE, null, ex);
            }
            URLConnection urlCon = urlObj.openConnection();

            Map<String, List<String>> map = urlCon.getHeaderFields();

            for (String key : map.keySet()) {
                System.out.println(key + ":");

                List<String> values = map.get(key);

                for (String aValue : values) {
                    System.out.println("\t" + aValue);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ReadAllServerHeaderFieldsProjectSinMaven.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
