package PSP05;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReadCommonHeaderFieldsSinMaven {

    public static void main(String[] args) {
         try {
            String url = "https://google.es";
            
            URL urlObj = new URL(url);
              HttpURLConnection httpCon = null;
            try {
                httpCon = (HttpURLConnection) urlObj.openConnection();
            
            
            int responseCode = 0;
            try {
                responseCode = httpCon.getResponseCode();
            } catch (IOException ex) {
                Logger.getLogger(ReadCommonHeaderFieldsSinMaven.class.getName()).log(Level.SEVERE, null, ex);
            }
            String responseMessage = httpCon.getResponseMessage();
            String contentType = httpCon.getContentType();
            String contentEncoding = httpCon.getContentEncoding();
            int contentLength = httpCon.getContentLength();
            
            long date = httpCon.getDate();
            long expiration = httpCon.getExpiration();
            long lastModified = httpCon.getLastModified();
            
            System.out.println("Response Code: " + responseCode);
            System.out.println("Response Message: " + responseMessage);
            System.out.println("Content Type: " + contentType);
            System.out.println("Content Encoding: " + contentEncoding);
            System.out.println("Content Length: " + contentLength);
            System.out.println("Date: " + new Date(date));
            System.out.println("Expiration: " + new Date(expiration));
            System.out.println("Last Modified: " + new Date(lastModified));
            
            } catch (IOException ex) {
                Logger.getLogger(ReadCommonHeaderFieldsSinMaven.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(ReadCommonHeaderFieldsSinMaven.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
