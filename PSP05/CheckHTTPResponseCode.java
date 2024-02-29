package PSP05;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CheckHTTPResponseCode {

    public static void main(String[] args) {

        // String url = "https://google.es"; //devuelve codigo 200
        String url = "http://facebook.com"; // devuelve codigo 301
        URL urlObj;

        try {
            urlObj = new URL(url);
        } catch (MalformedURLException ex) {
            Logger.getLogger(CheckHTTPResponseCode.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            urlObj = new URL(url);
            HttpURLConnection httpCon = null;
            try {
                httpCon = (HttpURLConnection) urlObj.openConnection();
            } catch (IOException ex) {
                Logger.getLogger(CheckHTTPResponseCode.class.getName()).log(Level.SEVERE, null, ex);
            }
            int responseCode = 0;
            try {
                responseCode = httpCon.getResponseCode();
            } catch (IOException ex) {
                Logger.getLogger(CheckHTTPResponseCode.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (responseCode != HttpURLConnection.HTTP_OK) {
                System.out.println("Server returned response code " + responseCode + ". Download failed.");
                // System.exit(0);
            } else {
                System.out.println("Server returned response code " + responseCode);

            }

        } catch (MalformedURLException ex) {
            Logger.getLogger(CheckHTTPResponseCode.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
