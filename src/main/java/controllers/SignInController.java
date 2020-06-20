package controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.UsernameAlreadyExistsException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.PlayerAgent;
import main.TeamManager;
import utils.DataManager;
import utils.PasswordHasher;
import utils.PathHolder;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.List;

public class SignInController {

    private List<HashMap<String, PlayerAgent>> pa_hash_list;
    private List<HashMap<String, TeamManager>> tm_hash_list;
    @FXML
    public PasswordField passwordField;
    @FXML
    public TextField usernameField;
    @FXML
    public Label invalidLabel;

    @FXML
    private void initialize()
    {
        invalidLabel.setText("");
    }

    public void backButtonPushed()
    {
        try {
            Stage stage = (Stage) usernameField.getScene().getWindow();
            Parent sign_up_root = FXMLLoader.load(new URL("file:///" + PathHolder.getPathToResourceFile("/fxml/welcome.fxml")));
            Scene scene = new Scene(sign_up_root, 800, 600);
            stage.setScene(scene);
            System.out.println("pressed back");
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public  void signInButtonPushed()
    {
        String username = usernameField.getText().toLowerCase();
        File f = new File(String.valueOf(PathHolder.getPathToResourceFile("user_data/credentials.json")));
        //read credentials
        ObjectMapper objectMapper = new ObjectMapper();
        List<HashMap<String, String>> credentials_list = null;
        try {
            credentials_list = objectMapper.readValue(f, new TypeReference<List<HashMap<String, String>>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        //check credentials
        int ok = 0;
        boolean matched = false;
        if(credentials_list.size() != 0)
        {
            for (HashMap<String, String> cred : credentials_list)
                if (cred.get("username").equals(username.toLowerCase())) //check for username
                {
                    ok = 1;
                    //check password
                    try {
                        matched = PasswordHasher.validatePassword(passwordField.getText(), cred.get("password"));
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    } catch (InvalidKeySpecException e) {
                        e.printStackTrace();
                    }
                    if (matched)
                    {
                        invalidLabel.setText("");
                        signInSuccessful(username, cred.get("role"));
                    }

                    else
                        invalidLabel.setText("Invalid password");
                    break;
                }
        }
        if(ok == 0)
            invalidLabel.setText("Username does not exist");

    }

    public int signInSuccessful(String username, String role)
    {
        System.out.println("Sign in as " + role);
        tm_hash_list = DataManager.readTM();
        pa_hash_list = DataManager.readPA();
        try {
            FXMLLoader loader = new FXMLLoader();
            if(role.equals("team manager"))
            {
                loader.setLocation(new URL("file:///" + PathHolder.getPathToResourceFile("/fxml/welcome_team_manager.fxml")));
                int ok = 0;
                if (tm_hash_list!= null)
                    for(HashMap<String, TeamManager> tm : tm_hash_list)
                        if(tm.containsKey(username)) {
                            loader.setLocation(new URL("file:///" + PathHolder.getPathToResourceFile("/fxml/team_manager_main.fxml")));
                            ok = 1;
                            break;
                        }
                Parent root= loader.load();
                if(ok == 0) {
                    WelcomeTMController controller;
                    controller = loader.getController();
                    controller.initData(username);
                }
                else
                {
                    MainTMController controller;
                    controller= loader.getController();
                    controller.initData(username);
                }

                Stage stage;
                if(usernameField.getScene() != null) {
                    stage = (Stage) usernameField.getScene().getWindow();
                    Scene scene = new Scene(root, 800, 600);
                    stage.setScene(scene);
                }
                else return ok;

            }

            else if(role.equals("player agent"))
            {
                {
                    System.out.println("Sign in as PA");
                    loader.setLocation(new URL("file:///" + PathHolder.getPathToResourceFile("/fxml/welcome_player_agent.fxml")));
                    int ok = 2;
                    if(pa_hash_list != null)
                        for(HashMap<String, PlayerAgent> pa : pa_hash_list)
                            if(pa.containsKey(username)) {
                                loader.setLocation(new URL("file:///" + PathHolder.getPathToResourceFile("/fxml/player_agent_main.fxml")));
                                ok = 3;
                                break;
                            }
                    Parent root= loader.load();
                    if(ok == 2) {
                        WelcomePAController controller;
                        controller = loader.getController();
                        controller.initData(username);

                    }
                    else
                    {
                        MainPAController controller;
                        controller= loader.getController();
                        controller.initData(username);
                    }
                    Stage stage;
                    if(usernameField.getScene()!= null) {
                        stage = (Stage) usernameField.getScene().getWindow();
                        Scene scene = new Scene(root, 800, 600);
                        stage.setScene(scene);
                    }
                    else return ok;
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return -1;
    }

}
