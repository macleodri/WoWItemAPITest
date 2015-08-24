import org.json.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * A class used to call the WoW API and return a JSON object to be parsed.
 * 
 * @author Robert MacLeod
 *
 */
public class WoWAPIConnection {
    private JSONObject jsonResponse;

    /**
     * Returns the JSON response from the API.
     * 
     * @return The response from the API as a JSONObject
     */
    public JSONObject getJSONResponse() {
        return jsonResponse;
    }

    /**
     * Makes a call to the API for the designated item. Stores the JSON response and
     * returns the response code in case the caller wants to check for errors.
     * 
     * @param itemInfo - information containing the id and context
     * @param apiKey - key used to gain access to the WoW API
     * @return int of the response code for the url connection
     */
    public int getJSONResponseFromAPI(String itemUrl, String apiKey) {
        String response = "";
        Scanner scan = null;
        int responseCode = 0;

        try {
            URL url = new URL(itemUrl + apiKey);

            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
            responseCode = urlConnection.getResponseCode();

            // Even if we failed to get the correct response from the server, still open the scanner to get information
            if((responseCode == 403)) {
                scan = new Scanner(urlConnection.getErrorStream());
            } else {
                scan = new Scanner(urlConnection.getInputStream());
            }

            while(scan.hasNext()) {
                response += scan.nextLine();
            }

            jsonResponse = new JSONObject(response);
        } catch (MalformedURLException e) {
            System.err.println("Incorrectly formed URL. Could not parse a valid protocol.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(scan != null) {
                scan.close();
            }
        }

        return responseCode;
    }
}
