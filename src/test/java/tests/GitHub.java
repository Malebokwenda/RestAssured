package tests;

import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class GitHub {
@Test
public static void gitHub() {
    String accessToken = "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";
    String apiUrl = "https://api.github.com/user";

    try {
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + accessToken);

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();
            String jsonResponse = response.toString();
            System.out.println("GitHub Profile Information:");
            System.out.println(jsonResponse);
        } else {
            System.out.println("Error: Unable to retrieve profile information. Response Code: " + responseCode);
        }
    } catch (ProtocolException e) {
        throw new RuntimeException(e);
    } catch (MalformedURLException e) {
        throw new RuntimeException(e);
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
    }


