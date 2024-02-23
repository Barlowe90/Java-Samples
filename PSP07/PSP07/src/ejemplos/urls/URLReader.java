package ejemplos.urls;

import java.net.*;
import java.io.*;

/*
 * This example illustrates using a URL to access resources
 * on a secure site.
 *
 * If you are running inside a firewall, please also set the following
 * Java system properties to the appropriate value:
 *
 *   https.proxyHost = <secure proxy server hostname>
 *   https.proxyPort = <secure proxy server port>
 *
 */
public class URLReader {

    public static void main(String[] args) throws Exception {
        URL verisign = new URL("https://www.verisign.com/");
        BufferedReader in = new BufferedReader(new InputStreamReader(verisign.openStream()));

        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
        }

        in.close();
    }
}
