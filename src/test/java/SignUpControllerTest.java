import controllers.SignUpController;
import exceptions.UsernameAlreadyExistsException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import utils.DataManager;
import utils.PathHolder;


import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SignUpControllerTest extends ApplicationTest {
    public static final String TEST_USERNAME = "UsernameTest";
    public static final String TEST_PASSWORD = "passwordTest";
    private SignUpController controller;
    private DataManager data_manager;

    @Before
    public void setup()
    {
        data_manager = new DataManager();
        data_manager.clearAllData();
        controller = new SignUpController();
        controller.usernameField = new TextField();
        controller.passwordField = new PasswordField();
        controller.rolebox = new ChoiceBox<>();

    }

    @Test
    public void signUpTest() throws Exception
    {
        controller.usernameField.setText(TEST_USERNAME);
        controller.passwordField.setText(TEST_PASSWORD);
        controller.signUpButtonPushed();
        assertNotNull(controller.getUsers());
        assertEquals(1, controller.getUsers().size());
    }

    @Test (expected = UsernameAlreadyExistsException.class)
    public void signUpDuplicateTest() throws Exception
    {
        controller.usernameField.setText(TEST_USERNAME);
        controller.passwordField.setText(TEST_PASSWORD);
        controller.signUpButtonPushed();
        controller.usernameField.setText(TEST_USERNAME);
        controller.passwordField.setText(TEST_PASSWORD);
        controller.signUpButtonPushed();
        assertNotNull(controller.getUsers());
        assertEquals(1, controller.getUsers().size());
    }

}
