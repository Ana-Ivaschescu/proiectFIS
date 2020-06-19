import controllers.SignUpController;
import exceptions.UsernameAlreadyExistsException;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.testfx.framework.junit.ApplicationTest;
import utils.DataManager;
import utils.PasswordHasher;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SignUpControllerTest extends ApplicationTest {

    private SignUpController controller;


    @BeforeClass
    public static void setupClass()
    {
        DataManager.clearAllData();
    }
    @Before
    public void setup()
    {
        controller = new SignUpController();
        controller.usernameField = new TextField();
        controller.passwordField = new PasswordField();
        controller.rolebox = new ChoiceBox<>();
    }

    @Test
    public void asignUpTestTM() throws Exception
    {
        controller.usernameField.setText(TestDataHolder.TEST_TM_USERNAME);
        controller.passwordField.setText(TestDataHolder.TEST_TM_PASSWORD);
        controller.rolebox.setValue(TestDataHolder.TEST_TM_ROLE);
        controller.signUpButtonPushed();
        assertNotNull(controller.getUsers());
        assertEquals(1, controller.getUsers().size());
    }

    @Test (expected = UsernameAlreadyExistsException.class)
    public void csignUpDuplicateTest() throws Exception
    {
        controller.usernameField.setText(TestDataHolder.TEST_TM_USERNAME);
        controller.passwordField.setText(TestDataHolder.TEST_TM_PASSWORD);
        controller.rolebox.setValue(TestDataHolder.TEST_TM_ROLE);
        controller.signUpButtonPushed();
        controller.usernameField.setText(TestDataHolder.TEST_TM_USERNAME);
        controller.passwordField.setText(TestDataHolder.TEST_TM_PASSWORD);
        controller.rolebox.setValue(TestDataHolder.TEST_TM_ROLE);
        controller.signUpButtonPushed();
        assertNotNull(controller.getUsers());
        assertEquals(1, controller.getUsers().size());
    }

    @Test
    public void bsignUpTestPA() throws Exception
    {
        controller.usernameField.setText(TestDataHolder.TEST_PA_USERNAME);
        controller.passwordField.setText(TestDataHolder.TEST_PA_PASSWORD);
        controller.rolebox.setValue(TestDataHolder.TEST_PA_ROLE);
        controller.signUpButtonPushed();
        assertNotNull(controller.getUsers());
        assertEquals(2,  controller.getUsers().size());
    }

    @Test
    public void testPasswordHash()
    {
        String password = "";
        try {
            password = PasswordHasher.Encrypt(TestDataHolder.TEST_TM_PASSWORD);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        boolean matched = false;
        try {
            matched = PasswordHasher.validatePassword(TestDataHolder.TEST_TM_PASSWORD, password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        assertEquals(true, matched);
    }

}
