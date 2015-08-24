import org.json.*;

/**
 * A class used to call the World of Warcraft Item API and return the
 * values for testing. Does not store the individual values from the returned
 * JSONObject given the small scope of testing.
 * 
 * @author Robert MacLeod
 *
 */
public class WoWItem {
    private String itemUrl = "https://us.api.battle.net/wow/item/"; // Url for the item API
    private JSONObject item; // The full JSON response from the server

    /**
     * Get the entire response from the API.
     * 
     * @return The response as a JSONObject
     */
    public JSONObject getFullResponse() {
        return item;
    }

    /**
     * Get the item's ID.
     * 
     * @return The item's ID
     */
    public int getID() {
        return item.getInt("id");
    }

    /**
     * Get the item's description.
     * 
     * @return The item's description
     */
    public String getDescription() {
        return item.getString("description");
    }

    /**
     * Get the item's name.
     * 
     * @return The item's name
     */
    public String getName() {
        return item.getString("name");
    }

    /**
     * Get the name of the item's icon.
     * 
     * @return The name of the item's icon
     */
    public String getIcon() {
        return item.getString("icon");
    }

    /**
     * Get the item's set info.
     * 
     * @return The item's set info as a JSONObject
     */
    public JSONObject getItemSet() {
        if(item.has("itemSet")) {
            return item.getJSONObject("itemSet");
        }
        else {
            return null;
        }
    }

    /**
     * Gets the corresponding item from the API and returns the response code.
     * 
     * @param itemInfo - information containing the item's id and context
     * @param apiKey - key used to gain access to the WoW API
     * @return int of the response code for the url connection (ex. 403 forbidden)
     */
    public int getItem(String itemInfo, String apiKey) {
        WoWAPIConnection apiConnection = new WoWAPIConnection();
        int responseCode = apiConnection.getJSONResponseFromAPI(itemUrl + itemInfo, apiKey);
        item = apiConnection.getJSONResponse();

        return responseCode;
    }
}
