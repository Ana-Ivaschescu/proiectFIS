package controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import exceptions.UsernameAlreadyExistsException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Text;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.PasswordHasher;
import utils.PathHolder;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.List;

public class SignUpController {

    ObservableList<String> roleList = FXCollections.observableArrayList("Team manager", "Player agent");
    @FXML
    public PasswordField passwordField;
    @FXML
    public TextField usernameField;
    @FXML
    public ChoiceBox<String> rolebox;

    @FXML
    private void initialize()
    {
        rolebox.setValue("Team manager");
        rolebox.setItems(roleList);

    }

    public void backButtonPushed()
    {
        try {
            Stage stage = (Stage) usernameField.getScene().getWindow();
            Parent sign_up_root = FXMLLoader.load(getClass().getResource("../fxml/welcome.fxml"));
            Scene scene = new Scene(sign_up_root, 800, 600);
            stage.setScene(scene);
            System.out.println("pressed back");
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public List<HashMap<String, String>> getUsers()
    {
        File f = new File(String.valueOf(PathHolder.getPathToResourceFile("user_data/credentials.json")));
        ObjectMapper objectMapper = new ObjectMapper();
        List<HashMap<String, String>> credentials_list = null;
        try {
            credentials_list = objectMapper.readValue(f, new TypeReference<List<HashMap<String, String>>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return credentials_list;
    }


    public void signUpButtonPushed() throws UsernameAlreadyExistsException
    {
        System.out.println(PathHolder.getPathToResourceFile("user_data/credentials.json"));
        File f = new File(String.valueOf(PathHolder.getPathToResourceFile("user_data/credentials.json")));
        //save credentials in a dict
        HashMap<String, String> credentials = new HashMap<>();
        credentials.put("username", usernameField.getText().toLowerCase());
        try {
            credentials.put("password", PasswordHasher.Encrypt(passwordField.getText()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        credentials.put("role", String.valueOf(rolebox.getValue()).toLowerCase());

        System.out.println(credentials.get("username") + " " + credentials.get("password"));

        //read other credentials

        List<HashMap<String, String>> credentials_list = getUsers();
        System.out.println(credentials_list.size());
        if(credentials_list.size() != 0)
            for(HashMap<String, String> cred : credentials_list)
                if(cred.get("username").equals(credentials.get("username")))
                    throw new UsernameAlreadyExistsException(credentials.get("username"));

        //add credentials
        credentials_list.add(credentials);
        ObjectMapper objectMapper = new ObjectMapper();
        //write new credentials to file
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(f, credentials_list);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*
        boolean matched = false;
        try {
            matched = PasswordHasher.validatePassword(passwordField.getText(), credentials.get("password"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
         */
        try {
            Stage stage;
            if(usernameField.getScene() != null) {
                stage = (Stage) usernameField.getScene().getWindow();

                Parent sign_up_root = FXMLLoader.load(getClass().getResource("../fxml/welcome.fxml"));
                Scene scene = new Scene(sign_up_root, 800, 600);
                stage.setScene(scene);
                System.out.println("sign in");
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
