package controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.PlayerAgent;
import main.Team;
import main.TeamManager;
import utils.PathHolder;

import javafx.scene.control.TextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class WelcomePAController {
    private String username;

    @FXML
    public Label usernameLabel;
    @FXML
    public TextField namefield;


    public void initData(String username)
    {
        this.username = username;
        usernameLabel.setText(this.username);
    }
    public void nextButtonPressed(){
        PlayerAgent pa = new PlayerAgent(namefield.getText());

        File f = new File(String.valueOf(PathHolder.getPathToResourceFile("user_data/player_agent.json")));
        HashMap<String,PlayerAgent> pa_map = new HashMap<>();
        pa_map.put(this.username, pa);

        ObjectMapper objectMapper = new ObjectMapper();
        List<HashMap<String, PlayerAgent>> pa_list = null;
        try {
            pa_list = objectMapper.readValue(f, new TypeReference<List<HashMap<String, PlayerAgent>>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        pa_list.add(pa_map);
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(f, pa_list);
        } catch (IOException e) {
            e.printStackTrace();
        }

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../fxml/player_agent_main.fxml"));
        Parent root= null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MainPAController controller;
        controller= loader.getController();
        controller.initData(username);
        Stage stage = (Stage) usernameLabel.getScene().getWindow();
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
    }


}


