import controllers.CheckPlayerAgentController;
import controllers.PACheckRequests;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import main.Player;
import main.PlayerAgent;
import main.Request;
import main.TeamManager;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.testfx.framework.junit.ApplicationTest;
import utils.DataManager;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RequestTest extends ApplicationTest {

    private static List<HashMap<String, TeamManager>> tm_hash_list;
    private static List<HashMap<String, PlayerAgent>> pa_hash_list;
    private CheckPlayerAgentController controller_tm;
    private PACheckRequests controller_pa;
    private Request r;
    private Player p;
    private PlayerAgent pa;
    private TeamManager tm;

    @BeforeClass
    public static  void preSetup()
    {
        TestDataHolder.populatePA();
        TestDataHolder.populateTM();
    }
    @Before
    public  void Setup()
    {
        tm_hash_list = DataManager.readTM();
        pa_hash_list = DataManager.readPA();
        //r = new Request(TestDataHolder.TEST_TM_NAME, TestDataHolder.TEST_PA_NAME, TestDataHolder.TEST_P_NAME);
        p = new Player(TestDataHolder.TEST_P_NAME, TestDataHolder.TEST_P_POS, TestDataHolder.TEST_P_DESC);

        //controller_tm stuff
        controller_tm = new CheckPlayerAgentController();
        controller_tm.player_list = new ListView<>();
        controller_tm.requestSentLabel = new Label();
        controller_tm.paNameLabel = new Label();
        controller_tm.initData(DataManager.searchPA(TestDataHolder.TEST_PA_NAME, pa_hash_list), DataManager.searchTM(TestDataHolder.TEST_TM_NAME, tm_hash_list),
                TestDataHolder.TEST_TM_USERNAME, pa_hash_list, tm_hash_list);

        //controlller_pa setup
        controller_pa = new PACheckRequests();
        controller_pa.request_list = new ListView<>();
        controller_pa.initData(TestDataHolder.TEST_PA_USERNAME, DataManager.searchPA(TestDataHolder.TEST_PA_NAME, pa_hash_list), tm_hash_list, pa_hash_list);
    }

    @Test
    public void aSendRequest()
    {
        controller_tm.player_list.getSelectionModel().select(0);
        controller_tm.requestButtonPressed();
        assertEquals("Request sent for player: " + p.getName(), controller_tm.requestSentLabel.getText());


    }
    @Test
    public void bSendRequestAgain()
    {
        controller_tm.player_list.getSelectionModel().select(0);
        controller_tm.requestButtonPressed();
        assertEquals(p.getName() + " already requested!", controller_tm.requestSentLabel.getText());

    }
    @Test
    public void cAcceptRequest()
    {
        controller_pa.request_list.getSelectionModel().select(0);
        controller_pa.acceptButtonPressed();
        List<HashMap<String, PlayerAgent>> pa_hash_list_new = DataManager.readPA();
        assertEquals("accept", DataManager.searchPA(TestDataHolder.TEST_PA_NAME, pa_hash_list_new).getRequest_list().get(0).getStatus());
        assertEquals(p.getName(), DataManager.searchTM(TestDataHolder.TEST_TM_NAME, tm_hash_list).getTeam().getPlayers().get(0).getName()); // check if player is added to team
    }
    @Test
    public void dDenyRequest()
    {
        controller_tm.player_list.getSelectionModel().select(1);
        controller_tm.requestButtonPressed();
        controller_pa.initData(TestDataHolder.TEST_PA_USERNAME, DataManager.searchPA(TestDataHolder.TEST_PA_NAME, pa_hash_list), tm_hash_list, pa_hash_list);
        controller_pa.request_list.getSelectionModel().select(1);

        controller_pa.denyButtonPressed();
        List<HashMap<String, PlayerAgent>> pa_hash_list_new = DataManager.readPA();
        assertEquals("deny", DataManager.searchPA(TestDataHolder.TEST_PA_NAME, pa_hash_list_new).getRequest_list().get(1).getStatus());
    }
}
