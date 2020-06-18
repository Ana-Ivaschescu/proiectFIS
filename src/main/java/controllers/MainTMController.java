package controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.TeamManager;
import utils.PathHolder;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class MainTMController {
    @FXML
    public Label teamNameLabel;
    @FXML
    public Label cityLabel;
    @FXML
    public Label leagueLabel;
    @FXML
    public Label descriptionLabel;
    @FXML
    public Label usernameLabel;
    private TeamManager tm;
    private String username;

    public void initData(String username)
    {
        this.username = username;
        //usernameLabel.setText(this.username);
        File f = new File(String.valueOf(PathHolder.getPathToResourceFile("user_data/team_manager.json")));
        ObjectMapper objectMapper = new ObjectMapper();
        List<HashMap<String, TeamManager>> tm_list = null;
        try {
            tm_list = objectMapper.readValue(f, new TypeReference<List<HashMap<String, TeamManager>>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (HashMap<String, TeamManager> stringTeamManagerHashMap : tm_list)
            if (stringTeamManagerHashMap.containsKey(username)) {
                System.out.println(stringTeamManagerHashMap.get(username));
                this.tm = stringTeamManagerHashMap.get(username);
                break;
            }
        teamNameLabel.setText(tm.getTeam().getName());
        cityLabel.setText(tm.getTeam().getCity());
        leagueLabel.setText(tm.getTeam().getLeague());
        descriptionLabel.setText(tm.getTeam().getDescription());
    }

    public void changeTeamDataPushed(){

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../fxml/change_team_data.fxml"));
        Parent root= null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ChangeTeamDataTMController controller;
        controller= loader.getController();
        controller.initData(username, teamNameLabel.getText(), cityLabel.getText(), leagueLabel.getText(), descriptionLabel.getText());
        Stage stage = (Stage) teamNameLabel.getScene().getWindow();
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
    }
    public void seeEditButtonPushed(){

    }


}
