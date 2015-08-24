import org.json.*;

/**
 * A class used to call the World of Warcraft Item Set API and return the
 * values for testing. Does not store the individual values from the returned
 * JSONObject given the small scope of testing.
 * 
 * @author Robert MacLeod
 *
 */
public class WoWItemSet {
    private String itemSetUrl = "https://us.api.battle.net/wow/item/set/"; // URL for the item set API
    private JSONObject itemSet; // The full response from the server

    /**
     * Get the entire response from the API.
     * 
     * @return The response as a JSONObject
     */
    public JSONObject getFullResponse() {
        return itemSet;
    }

    /**
     * Get the set's id.
     * 
     * @return The set's id
     */
    public int getID() {
        return itemSet.getInt("id");
    }

    /**
     * Get the set's name.
     * 
     * @return The set's name
     */
    public String getName() {
        return itemSet.getString("name");
    }

    /**
     * Parse the 'setBonuses' section of the response and add the values to a String[][]
     * with the format {{description, threshold},{...}}
     * 
     * @return The set bonuses as String[][]
     */
    public String[][] getSetBonuses() {
        JSONArray setBonusArray = itemSet.getJSONArray("setBonuses");

        String[][] setBonuses = new String[setBonusArray.length()][2]; // The list of set bonuses | Format: {{description, threshold},{...}}

        for(int i = 0; i < setBonusArray.length(); i++) {
            setBonuses[i][0] = setBonusArray.getString(0);
            setBonuses[i][1] = Integer.toString(setBonusArray.getInt(1));
        }

        return setBonuses;
    }

    /**
     * Get the IDs for the items contained in the sets.
     * 
     * @return An array containing the ids of the items in the set
     */
    public int[] getItems() {
        JSONArray itemList = itemSet.getJSONArray("items");
        int[] items = new int[itemList.length()]; // IDs for each of the items contained in the item set
        for(int i = 0; i < items.length; i++) {
            items[i] = itemList.getInt(i);
        }

        return items;
    }

    /**
     * Gets the corresponding item set from the API and returns the response code.
     * 
     * @param itemSetInfo - information containing the set's id
     * @param apiKey - key used to gain access to the WoW API
     * @return int of the response code for the url connection (ex. 403 forbidden)
     */
    public int getItemSet(String itemSetInfo, String apiKey) {
        WoWAPIConnection apiConnection = new WoWAPIConnection();
        int responseCode = apiConnection.getJSONResponseFromAPI(itemSetUrl + itemSetInfo, apiKey);
        itemSet = apiConnection.getJSONResponse();

        return responseCode;
    }
}
