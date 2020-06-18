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
import utils.PasswordHasher;
import utils.PathHolder;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.List;

public class SignInController {

    @FXML
    public PasswordField passwordField;
    @FXML
    public TextField usernameField;
    @FXML
    public Label invalidLabel;

    @FXML
    private void initialize()
    {
        invalidLabel.setText("Test");
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

    private void signInSuccessful(String username, String role)
    {
        System.out.println("Sign in as " + role);
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            FXMLLoader loader = new FXMLLoader();
            if(role.equals("team manager"))
            {
                System.out.println("Sign in as TM");
                //read tm lists
                File f = new File(String.valueOf(PathHolder.getPathToResourceFile("user_data/team_manager.json")));
                List<HashMap<String, TeamManager>> tm_list = null;
                try {
                    tm_list = objectMapper.readValue(f, new TypeReference<List<HashMap<String, TeamManager>>>(){});
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //System.out.println(tm_list);

                loader.setLocation(getClass().getResource("../fxml/welcome_team_manager.fxml"));
                int ok = 0;
                if (tm_list!= null)
                    for(HashMap<String, TeamManager> tm : tm_list)
                        if(tm.containsKey(username)) {
                            loader.setLocation(getClass().getResource("../fxml/team_manager_main.fxml"));
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
                Stage stage = (Stage) usernameField.getScene().getWindow();
                Scene scene = new Scene(root, 800, 600);
                stage.setScene(scene);

            }

            else if(role.equals("player agent"))
            {
                {
                    System.out.println("Sign in as PA");
                    //read tm lists
                    File f = new File(String.valueOf(PathHolder.getPathToResourceFile("user_data/player_agent.json")));
                    List<HashMap<String, PlayerAgent>> pa_list = null;
                    try {
                        pa_list = objectMapper.readValue(f, new TypeReference<List<HashMap<String, PlayerAgent>>>(){});
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //System.out.println(pa_list);

                    loader.setLocation(getClass().getResource("../fxml/welcome_player_agent.fxml"));
                    int ok = 0;
                    if(pa_list != null)
                        for(HashMap<String, PlayerAgent> pa : pa_list)
                            if(pa.containsKey(username)) {
                                loader.setLocation(getClass().getResource("../fxml/player_agent_main.fxml"));
                                ok = 1;
                                break;
                            }
                    Parent root= loader.load();
                    if(ok == 0) {
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
                    Stage stage = (Stage) usernameField.getScene().getWindow();
                    Scene scene = new Scene(root, 800, 600);
                    stage.setScene(scene);

                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
