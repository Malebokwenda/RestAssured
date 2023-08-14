package tests;

import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class ErrorHandlingAPI {
@Test
    public static void errorHandling() {
        String apiUrl = "https://jsonplaceholder.typicode.com/posts/140";

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_NOT_FOUND) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                in.close();
                String errorResponse = response.toString();
                System.out.println("Error Response:");
                System.out.println("Response Code: " + responseCode);
                System.out.println(errorResponse);
            } else {
                System.out.println("Unexpected Response Code: " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

