import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import main.PlayerAgent;
import main.TeamManager;
import utils.PathHolder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

public class TestDataHolder {
    private static String credentials_path = String.valueOf(PathHolder.getPathToResourceFile("user_data/credentials.json"));
    private static String tm_path = String.valueOf(PathHolder.getPathToResourceFile("user_data/team_manager.json"));
    private static String pa_path = String.valueOf(PathHolder.getPathToResourceFile("user_data/player_agent.json"));

    public static final String TEST_TM_USERNAME = "tmtest";
    public static final String TEST_TM_PASSWORD = "tmtest";
    public static final String TEST_TM_ROLE = "team manager";
    public static final String TEST_TM_NAME = "tm_test_name";

    public static final String TEST_TEAM_NAME = "test_team_name";
    public static final String TEST_TEAM_CITY = "test_city_name";
    public static final String TEST_TEAM_LEAGUE = "test_league_name";
    public static final String TEST_TEAM_DESC = "test_description_name";

    public static final String TEST_TEAM_NAME_CHANGED = "test_team_name_changed";
    public static final String TEST_TEAM_CITY_CHANGED = "test_city_name_changed";
    public static final String TEST_TEAM_LEAGUE_CHANGED = "test_league_name_changed";
    public static final String TEST_TEAM_DESC_CHANGED = "test_description_name_changed";


    public static final String TEST_PA_USERNAME = "patest";
    public static final String TEST_PA_PASSWORD = "patest";
    public static final String TEST_PA_ROLE = "player agent";
    public static final String TEST_PA_NAME = "pa_name_test";

    public static final String TEST_P_NAME = "player_name_1";
    public static final String TEST_P_POS = "guard";
    public static final String TEST_P_DESC = "player_description_1";
    public static final boolean TEST_P_AV = true;

    public static final String TEST_P_POS_CHANGED = "wing";
    public static final String TEST_P_DESC_CHANGED = "player_description_1_changed";

    public static final String TEST_P_NAME_NEW = "new_player_name_1";
    public static final String TEST_P_POS_NEW = "center";
    public static final String TEST_P_DESC_NEW = "new_player_description_1";




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
                "      \"name\" : \"test_team_name\",\n" +
                "      \"city\" : \"test_city_name\",\n" +
                "      \"league\" : \"test_league_name\",\n" +
                "      \"description\" : \"test_description_name\",\n" +
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
