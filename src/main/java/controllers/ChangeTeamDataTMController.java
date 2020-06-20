package controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.TeamManager;
import utils.DataManager;
import utils.PathHolder;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

public class ChangeTeamDataTMController {
    @FXML
    public TextField teamNameField;
    @FXML
    public TextField cityField;
    @FXML
    public TextField leagueField;
    @FXML
    public TextField descriptionField;
    private String username;
    public void initData(String username, String team_name, String city, String league, String description)
    {
        this.username = username;
        teamNameField.setText(team_name);
        cityField.setText(city);
        leagueField.setText(league);
        descriptionField.setText(description);
    }
    public void saveButtonPushed(){
        String teamName = teamNameField.getText();
        String teamCity = cityField.getText();
        String teamLeague = leagueField.getText();
        String teamDescription = descriptionField.getText();

        List<HashMap<String, TeamManager>> tm_hash_list = null;
        tm_hash_list = DataManager.readTM();
        for(int i=0; i<tm_hash_list.size(); i++)
            if(tm_hash_list.get(i).containsKey(username)) {
                tm_hash_list.get(i).get(username).getTeam().setName(teamName);
                tm_hash_list.get(i).get(username).getTeam().setCity(teamCity);
                tm_hash_list.get(i).get(username).getTeam().setLeague(teamLeague);
                tm_hash_list.get(i).get(username).getTeam().setDescription(teamDescription);
                break;
            }
        DataManager.saveTM(tm_hash_list);
        Stage stage;
        if(teamNameField.getScene() != null) {
            FXMLLoader loader = new FXMLLoader();
            try {
                loader.setLocation(new URL("file:///" + PathHolder.getPathToResourceFile("/fxml/team_manager_main.fxml")));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            MainTMController controller;
            controller = loader.getController();
            controller.initData(username);
            stage = (Stage) teamNameField.getScene().getWindow();
            Scene scene = new Scene(root, 800, 600);
            stage.setScene(scene);
        }
    }
}
