import controllers.SignInController;
import controllers.SignUpController;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.testfx.framework.junit.ApplicationTest;
import utils.DataManager;

import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SignInControllerTest extends ApplicationTest {

    private SignInController controller;


    @Before
    public void setup()
    {
        DataManager.clearTMData();
        DataManager.clearPAData();
        controller = new SignInController();
        controller.usernameField = new TextField();
        controller.passwordField = new PasswordField();
        controller.invalidLabel = new Label();
    }
    @Test
    public void signInTestWrongPassword()
    {
        controller.usernameField.setText(TestDataHolder.TEST_TM_USERNAME);
        controller.passwordField.setText(TestDataHolder.TEST_FAIL_PASSWORD);
        controller.signInButtonPushed();
        assertEquals("Invalid password", controller.invalidLabel.getText());
    }
    @Test
    public void signInTestNoUsername()
    {
        controller.usernameField.setText(TestDataHolder.TEST_NO_USERNAME);
        controller.passwordField.setText(TestDataHolder.TEST_TM_PASSWORD);
        controller.signInButtonPushed();
        assertEquals("Username does not exist", controller.invalidLabel.getText());
    }
    @Test
    public void asignInTestTMFirst()
    {
        assertEquals(0, controller.signInSuccessful(TestDataHolder.TEST_TM_USERNAME, TestDataHolder.TEST_TM_ROLE));
    }
    @Test
    public void bsignInTestTMSecond()
    {
        TestDataHolder.populateTM();
        assertEquals(1, controller.signInSuccessful(TestDataHolder.TEST_TM_USERNAME, TestDataHolder.TEST_TM_ROLE));
    }

    @Test
    public void csignInTestPAFirst()
    {
        assertEquals(2, controller.signInSuccessful(TestDataHolder.TEST_PA_USERNAME, TestDataHolder.TEST_PA_ROLE));
    }

    @Test
    public void dsignInTestPASecond()
    {
        TestDataHolder.populatePA();
        assertEquals(3, controller.signInSuccessful(TestDataHolder.TEST_PA_USERNAME, TestDataHolder.TEST_PA_ROLE));
    }
}
