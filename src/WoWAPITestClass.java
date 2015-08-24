import org.junit.*;
import static org.junit.Assert.*;

/**
 * A class used to test the item API for World of Warcraft. Contains
 * a non-exhaustive series of test cases used as examples for special
 * cases one might encounter when making calls to the API.
 * 
 * This class is run by the WoWAPITestRunner.
 * 
 * @author Robert MacLeod
 *
 */
public class WoWAPITestClass {
    private String apiKey = "apikey=gwcmpqbmwsssupvbhg5fn6dd4en7szuv"; // The key used to make calls to the API. Can be changed if it becomes invalid.

    /**
     * Tests to see if the proper return code is received if an invalid
     * item ID is given to the URL request. Test fails if an incorrect
     * response code is returned.
     */
    @Test
    public void testCaseItemInvalidID() {
        WoWItem invalidItemID = new WoWItem();
        int returnCode = invalidItemID.getItem("", apiKey);

        assertEquals("Invalid return code. Result: " + returnCode + " Expected : 403 | Full response: "
                     + invalidItemID.getFullResponse(), returnCode, 403);
    }

    /**
     * Tests to see if the proper return code is received if an invalid
     * API key is given to the URL request. Test fails if an incorrect
     * response code is returned.
     */
    @Test
    public void testCaseItemSetNoAPIKey() {
        WoWItemSet invalidAPIKey = new WoWItemSet();
        int returnCode = invalidAPIKey.getItemSet("672?", "");

        assertEquals("Invalid return code. Result: " + returnCode + " Expected : 403 | Full response: "
                     + invalidAPIKey.getFullResponse(), returnCode, 403);
    }

    /**
     * Tests to see if two items with the same ID but generated from 
     * different contexts still contain matching information. 
     */
    @Test
    public void testCaseSameItemDifferentContext() {
        WoWItem mythicEredarLord = new WoWItem();
        mythicEredarLord.getItem("124176/raid-mythic?", apiKey);
        WoWItem heroicEredarLord = new WoWItem();
        heroicEredarLord.getItem("124176/raid-heroic?", apiKey);

        String name1 = mythicEredarLord.getName();
        String name2 = heroicEredarLord.getName();

        assertEquals("Item names do not match. Item 1: " + name1 + " Item 2: "
                     + name2, name1, name2);
    }

    /**
     * Tests to see if two different items still share the same icon.
     */
    @Test
    public void testCaseSameIconDifferentItem() {
        WoWItem ghostDye = new WoWItem();
        ghostDye.getItem("9210?", apiKey);
        WoWItem elixirOfShadows = new WoWItem();
        elixirOfShadows.getItem("32446?", apiKey);

        String icon1 = ghostDye.getIcon();
        String icon2 = elixirOfShadows.getIcon();

        assertEquals("Icons do not match. Item 1: " + icon1 + " Item 2: " + icon2, icon1, icon2);

    }

    /**
     * Tests to see if each member of a particular item set is associated with
     * the correct set.
     */
    @Test
    public void testCaseEachItemContainsCorrectItemSet() {
        WoWItemSet ChainOfTheScarletCrusade = new WoWItemSet();
        ChainOfTheScarletCrusade.getItemSet("163?", apiKey);

        String scarletSetName = ChainOfTheScarletCrusade.getName();
        int scarletSetIDs[] = ChainOfTheScarletCrusade.getItems();

        WoWItem itemToCompare = new WoWItem();
        String nameToCompare = null;
        boolean contains = true;
        for(int i = 0; i < scarletSetIDs.length; i++) {
            itemToCompare.getItem(scarletSetIDs[i] + "?", apiKey);
            nameToCompare = itemToCompare.getItemSet().getString("name");

            // If the set names do not match, stop looking
            if(nameToCompare.compareTo(scarletSetName) != 0) {
                contains = false;
                break;
            }
        }

        assertTrue("Item does not have correct set. Item: " + itemToCompare.getName() + " | Expected set: " + 
                   scarletSetName + " Actual set: " + nameToCompare, contains);
    }

    /**
     * Tests to see if an item set contains a particular item.
     */
    @Test
    public void testCaseItemSetContainsSpecificItem() {
        WoWItemSet onslaughtBattlegear = new WoWItemSet();
        onslaughtBattlegear.getItemSet("672?", apiKey);

        int[] setIDs = onslaughtBattlegear.getItems();
        int idToCheck = 30972;
        boolean contains = false;
        for(int i = 0; i < setIDs.length; i++) {
            if(setIDs[i] == idToCheck) {
                contains = true;
            }
        }

        assertTrue("Item set does not id: " + idToCheck + " | Full response: " + onslaughtBattlegear.getFullResponse(), contains);
    }
}
