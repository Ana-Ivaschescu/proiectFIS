import controllers.ChangeTeamDataTMController;
import controllers.TeamDataPopupController;
import javafx.scene.control.TextField;
import main.TeamManager;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import utils.DataManager;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ChangeTeamDataTest extends ApplicationTest {
    private ChangeTeamDataTMController controller;
    private static List<HashMap<String, TeamManager>> tm_hash_list;
    private TeamManager tm;

    @BeforeClass
    public static void preSetup()
    {
        TestDataHolder.populateTM();
    }
    @Before
    public void setup()
    {
        controller = new ChangeTeamDataTMController();
        controller.descriptionField = new TextField();
        controller.teamNameField = new TextField();
        controller.cityField = new TextField();
        controller.leagueField = new TextField();
        controller.initData(TestDataHolder.TEST_TM_USERNAME, TestDataHolder.TEST_TEAM_NAME, TestDataHolder.TEST_TEAM_CITY,
                TestDataHolder.TEST_TEAM_LEAGUE, TestDataHolder.TEST_TEAM_DESC);

    }
    @Test
    public void changeDataTest()
    {
        controller.descriptionField.setText(TestDataHolder.TEST_TEAM_DESC_CHANGED);
        controller.leagueField.setText(TestDataHolder.TEST_TEAM_LEAGUE_CHANGED);
        controller.cityField.setText(TestDataHolder.TEST_TEAM_CITY_CHANGED);
        controller.teamNameField.setText(TestDataHolder.TEST_TEAM_NAME_CHANGED);
        controller.saveButtonPushed();
        tm_hash_list = DataManager.readTM();
        tm = DataManager.searchTM(TestDataHolder.TEST_TM_NAME, tm_hash_list);
        assertEquals(TestDataHolder.TEST_TEAM_DESC_CHANGED, tm.getTeam().getDescription());
        assertEquals(TestDataHolder.TEST_TEAM_CITY_CHANGED, tm.getTeam().getCity());
        assertEquals(TestDataHolder.TEST_TEAM_LEAGUE_CHANGED, tm.getTeam().getLeague());
        assertEquals(TestDataHolder.TEST_TEAM_NAME_CHANGED, tm.getTeam().getName());
    }
}
