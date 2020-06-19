import controllers.AddPlayerController;
import controllers.EditPlayerController;
import main.Player;
import main.PlayerAgent;
import main.TeamManager;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import utils.DataManager;

import java.util.HashMap;
import java.util.List;

public class AddEditPlayerTest {

    private static List<HashMap<String, TeamManager>> tm_hash_list;
    private static List<HashMap<String, PlayerAgent>> pa_hash_list;
    private AddPlayerController controller_add;
    private EditPlayerController controller_edit;
    private Player p;

    @BeforeClass
    public static void preSetup()
    {
        TestDataHolder.populateTM();
        TestDataHolder.populatePA();

    }
    @Before
    public void setup()
    {
        tm_hash_list = DataManager.readTM();
        pa_hash_list = DataManager.readPA();
        p = new Player(TestDataHolder.TEST_P_NAME, TestDataHolder.TEST_P_POS, TestDataHolder.TEST_P_DESC);
    }

    @Test
    public void addPlayerTest()
    {

    }
    public void editPlayerTest()
    {

    }
}
