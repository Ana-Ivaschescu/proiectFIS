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
import utils.DataManager;
import utils.PathHolder;

import javafx.scene.control.TextField;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
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

        HashMap<String,TeamManager> team_m = new HashMap<>();
        team_m.put(this.username, tm);

        List<HashMap<String, TeamManager>> tm_list = null;
        tm_list = DataManager.readTM();
        tm_list.add(team_m);
        DataManager.saveTM(tm_list);

        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(new URL("file:///" + PathHolder.getPathToResourceFile("/fxml/team_manager_main.fxml")));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Parent root= null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MainTMController controller;
        controller= loader.getController();
        controller.initData(username);
        Stage stage = (Stage) team_namefield.getScene().getWindow();
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
    }


}


