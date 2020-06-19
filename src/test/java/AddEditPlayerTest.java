import controllers.AddPlayerController;
import controllers.EditPlayerController;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import main.Player;
import main.PlayerAgent;
import main.TeamManager;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import utils.DataManager;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AddEditPlayerTest extends ApplicationTest {

    private static List<HashMap<String, TeamManager>> tm_hash_list;
    private static List<HashMap<String, PlayerAgent>> pa_hash_list;
    private AddPlayerController controller_add;
    private EditPlayerController controller_edit;
    private Player p_new;

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
        p_new = new Player(TestDataHolder.TEST_P_NAME_NEW, TestDataHolder.TEST_P_POS_NEW, TestDataHolder.TEST_P_DESC_NEW);

        controller_add = new AddPlayerController();
        controller_add.playerNameField = new TextField();
        controller_add.positionBox = new ChoiceBox<>();
        controller_add.playerDescriptionField = new TextField();
        controller_add.initData(TestDataHolder.TEST_PA_USERNAME);

    }

    @Test
    public void addPlayerTest()
    {
        controller_add.playerNameField.setText(TestDataHolder.TEST_P_NAME_NEW);
        controller_add.positionBox.setValue(TestDataHolder.TEST_P_POS_NEW);
        controller_add.playerDescriptionField.setText(TestDataHolder.TEST_P_DESC_NEW);
        controller_add.saveButtonPushed();
        pa_hash_list = DataManager.readPA();
        tm_hash_list = DataManager.readTM();
        PlayerAgent pa = DataManager.searchPA(TestDataHolder.TEST_PA_NAME, pa_hash_list);
        assertEquals(TestDataHolder.TEST_P_NAME_NEW, pa.getPlayers().get(2).getName());
        assertEquals(TestDataHolder.TEST_P_POS_NEW, pa.getPlayers().get(2).getPlaying_position());
        assertEquals(TestDataHolder.TEST_P_DESC_NEW, pa.getPlayers().get(2).getDescription());

    }
    public void editPlayerTest()
    {

    }
}
