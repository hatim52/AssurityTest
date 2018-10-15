package com.assurity.tech;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * URLReader class is used to invoke a public URL and print the corresponding response
 */
public class URLReader {

    /**
     * The read method accepts the url String as input and returns the reponse as a String
     * It also prints the response on the console.
     * @param url
     * @return
     * @throws Exception
     */
    public static String read(String url) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");
        //add request header
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = con.getResponseCode();
        //Validate whether we have received any response code at all.
        ArgumentValidator.checkArgumentNullOrEmpty (responseCode);
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);
        //Read the response
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        ArgumentValidator.checkArgumentNullOrEmpty (response.toString ());
        //print the response String
        System.out.println(response.toString());
        return response.toString ();
    }
}
