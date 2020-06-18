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

public class WelcomeTMController {
    private String username;

    @FXML
    public Label usernameLabel;
    @FXML
    public TextField namefield;
    @FXML
    public TextField team_namefield;

    public void initData(String username)
    {
        this.username = username;
        usernameLabel.setText(this.username);
    }
    public void nextButtonPressed(){
        TeamManager tm = new TeamManager(namefield.getText());
        Team t = new Team(team_namefield.getText(),"","", "");
        tm.setTeam(t);
        File f = new File(String.valueOf(PathHolder.getPathToResourceFile("user_data/team_manager.json")));
        HashMap<String,TeamManager> team_m = new HashMap<>();
        team_m.put(this.username, tm);

        ObjectMapper objectMapper = new ObjectMapper();
        List<HashMap<String, TeamManager>> tm_list = null;
        try {
            tm_list = objectMapper.readValue(f, new TypeReference<List<HashMap<String, TeamManager>>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        tm_list.add(team_m);
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(f, tm_list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
