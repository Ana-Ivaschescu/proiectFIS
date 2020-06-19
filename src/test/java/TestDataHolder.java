import utils.PathHolder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class TestDataHolder {
    private static String credentials_path = String.valueOf(PathHolder.getPathToResourceFile("user_data/credentials.json"));
    private static String tm_path = String.valueOf(PathHolder.getPathToResourceFile("user_data/team_manager.json"));
    private static String pa_path = String.valueOf(PathHolder.getPathToResourceFile("user_data/player_agent.json"));

    public static final String TEST_TM_USERNAME = "tmtest";
    public static final String TEST_TM_PASSWORD = "tmtest";
    public static final String TEST_TM_ROLE = "team manager";

    public static final String TEST_PA_USERNAME = "patest";
    public static final String TEST_PA_PASSWORD = "patest";
    public static final String TEST_PA_ROLE = "player agent";

    public static final String TEST_FAIL_PASSWORD = "this is not a password";
    public static final String TEST_NO_USERNAME = "this username does not exist";

    public static void populateTM()
    {

        File f = new File(tm_path);
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        writer.print("[ {\n" +
                "  \"tmtest\" : {\n" +
                "    \"name\" : \"tm_test_name\",\n" +
                "    \"team\" : {\n" +
                "      \"name\" : \"tm_team_test_name\",\n" +
                "      \"city\" : \"city_name_1\",\n" +
                "      \"league\" : \"league_1\",\n" +
                "      \"description\" : \"team_description_1\",\n" +
                "      \"players\" : [ ]\n" +
                "    },\n" +
                "    \"request_list\" : [ ]\n" +
                "  }\n" +
                "} ]");
        writer.close();
    }

    public static void populatePA()
    {

        File f = new File(pa_path);
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        writer.print("    \n" +
                "    [ {\n" +
                "        \"patest\" : {\n" +
                "            \"name\" : \"pa_name_test\",\n" +
                "                    \"request_list\" : [ ],\n" +
                "            \"players\" : [ {\n" +
                "                \"name\" : \"player_name_1\",\n" +
                "                        \"playing_position\" : \"guard\",\n" +
                "                        \"description\" : \"player_description_1\",\n" +
                "                        \"available\" : true\n" +
                "            }, {\n" +
                "                \"name\" : \"player_name_2\",\n" +
                "                        \"playing_position\" : \"wing\",\n" +
                "                        \"description\" : \"player_description_2\",\n" +
                "                        \"available\" : true\n" +
                "            } ]\n" +
                "        }\n" +
                "    } ]");
        writer.close();
    }
}
