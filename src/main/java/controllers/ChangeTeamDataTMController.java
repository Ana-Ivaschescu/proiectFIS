package controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.TeamManager;
import utils.PathHolder;

import java.io.File;
import java.io.IOException;
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

        File f = new File(String.valueOf(PathHolder.getPathToResourceFile("user_data/team_manager.json")));
        ObjectMapper objectMapper = new ObjectMapper();
        List<HashMap<String, TeamManager>> tm_list = null;
        try {
            tm_list = objectMapper.readValue(f, new TypeReference<List<HashMap<String, TeamManager>>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(int i=0; i<tm_list.size(); i++)
            if(tm_list.get(i).containsKey(username)) {
                tm_list.get(i).get(username).getTeam().setName(teamName);
                tm_list.get(i).get(username).getTeam().setCity(teamCity);
                tm_list.get(i).get(username).getTeam().setLeague(teamLeague);
                tm_list.get(i).get(username).getTeam().setDescription(teamDescription);
                break;
            }
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(f, tm_list);
        } catch (IOException e) {
            e.printStackTrace();
        }

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../fxml/team_manager_main.fxml"));
        Parent root= null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MainTMController controller;
        controller= loader.getController();
        controller.initData(username);
        Stage stage = (Stage) teamNameField.getScene().getWindow();
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
    }
}
