import main.Player;
import main.PlayerAgent;
import main.Team;
import main.TeamManager;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import utils.DataManager;
import utils.PathHolder;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class SaveLoadTest extends ApplicationTest {

    private static List<HashMap<String, TeamManager>> tm_hash_list;
    private static List<HashMap<String, PlayerAgent>> pa_hash_list;
    private PlayerAgent pa;
    private TeamManager tm;

    @BeforeClass
    public static void preSetup()
    {

    }
    @Before
    public void setup()
    {

    }

    @Test
    public void saveData()
    {
        //clear data
        DataManager.clearTMData();
        DataManager.clearPAData();
        //check if data is cleared
        File f = new File(String.valueOf(PathHolder.getPathToResourceFile("user_data/team_manager.json")));
        assertEquals(2, f.length());
        f = new File(String.valueOf(PathHolder.getPathToResourceFile("user_data/player_agent.json")));
        assertEquals(2, f.length());
        //check if the lists read are empty
        tm_hash_list = DataManager.readTM();
        pa_hash_list = DataManager.readPA();
        assertEquals(0, tm_hash_list.size());
        assertEquals(0, pa_hash_list.size());
        //populate lists
        HashMap<String, TeamManager> temp_tm = new HashMap<>();
        tm = new TeamManager(TestDataHolder.TEST_TM_NAME);
        tm.setTeam(new Team(TestDataHolder.TEST_TEAM_NAME, TestDataHolder.TEST_TEAM_CITY, TestDataHolder.TEST_TEAM_LEAGUE, TestDataHolder.TEST_TEAM_DESC));
        temp_tm.put(TestDataHolder.TEST_TM_USERNAME, tm);
        tm_hash_list.add(temp_tm);
        HashMap<String, PlayerAgent> temp_pa = new HashMap<>();
        temp_pa.put(TestDataHolder.TEST_PA_USERNAME, new PlayerAgent(TestDataHolder.TEST_PA_NAME));
        pa_hash_list.add(temp_pa);
        //save
        DataManager.saveAll(tm_hash_list, pa_hash_list);
        //check if smth is written
        f = new File(String.valueOf(PathHolder.getPathToResourceFile("user_data/team_manager.json")));
        assertNotEquals(2, f.length());
        f = new File(String.valueOf(PathHolder.getPathToResourceFile("user_data/player_agent.json")));
        assertNotEquals(2, f.length());
    }
    @Test
    public void loadData()
    {
        TestDataHolder.populatePA();
        TestDataHolder.populateTM();
        tm_hash_list = null;
        pa_hash_list = null;
        tm_hash_list = DataManager.readTM();
        pa_hash_list = DataManager.readPA();
        assertNotNull(tm_hash_list);
        assertNotNull(pa_hash_list);
        tm = null;
        pa = null;
        pa = DataManager.searchPA(TestDataHolder.TEST_PA_NAME, pa_hash_list);
        tm = DataManager.searchTM(TestDataHolder.TEST_TM_NAME, tm_hash_list);
        assertNotNull(pa);
        assertNotNull(tm);
        assertEquals(TestDataHolder.TEST_PA_NAME, pa.getName());
        assertEquals(TestDataHolder.TEST_TM_NAME, tm.getName());
    }
}
