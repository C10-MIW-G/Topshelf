package nl.miwgroningen.ch10.topshelf.security.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;

/**
 * @author Jack Wieringa <j.w.wieringa@st.hanze.nl>
 * Verifies the reCaptcha key which is provided by the frontend input
 */

@Service
@RequiredArgsConstructor
public class CaptchaService {

    private final CaptchaSettings captchaSettings;
    public static final String GOOGLE_URL = "https://www.google.com/recaptcha/api/siteverify";
    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String VALIDATION_STRING = "success";

    public boolean verify(String captchaResponse) {
        String urlString = "secret=" + captchaSettings.getSecretKey() + "&response="
                + captchaResponse;

        if (captchaResponse == null || "".equals(captchaResponse)) {
            return false;
        }
        try {
            HttpsURLConnection connection = getHttpsURLConnection();
            addOutputToCurrentConnection(urlString, connection);

            StringBuilder response = convertResponseToString(connection);

            return checkForValidResponse(response);

        } catch (Exception exception) {
            return false;
        }
    }

    private HttpsURLConnection getHttpsURLConnection() throws IOException {
        URL url = new URL(GOOGLE_URL);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

        connection.setRequestMethod("POST");
        connection.setRequestProperty("User-Agent", USER_AGENT);
        connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        connection.setDoOutput(true);

        return connection;
    }

    private void addOutputToCurrentConnection(String urlString, HttpsURLConnection connection) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());

        dataOutputStream.writeBytes(urlString);
        dataOutputStream.flush();
        dataOutputStream.close();
    }

    private static StringBuilder convertResponseToString(HttpsURLConnection connection) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(
                connection.getInputStream()));

        StringBuilder response = new StringBuilder();
        String inputLine;
        while ((inputLine = input.readLine()) != null) {
            response.append(inputLine);
        }
        input.close();

        return response;
    }

    private boolean checkForValidResponse(StringBuilder response) {
        JsonReader jsonReader = Json.createReader(new StringReader(response.toString()));
        JsonObject jsonObject = jsonReader.readObject();
        jsonReader.close();

        return jsonObject.getBoolean(VALIDATION_STRING);
    }
}
