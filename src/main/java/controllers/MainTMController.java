package controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import main.PlayerAgent;
import main.TeamManager;
import utils.PathHolder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
    @FXML
    public ListView<String> paListView = new ListView<>();
    private TeamManager tm;
    private String username;
    private ArrayList<PlayerAgent> pa_list = new ArrayList<PlayerAgent>();
    private List<HashMap<String, PlayerAgent>> pa_hash;
    private List<HashMap<String, TeamManager>> tm_hash;

    public void initData(String username)
    {
        this.username = username;
        //usernameLabel.setText(this.username);
        File f = new File(String.valueOf(PathHolder.getPathToResourceFile("user_data/team_manager.json")));
        ObjectMapper objectMapper = new ObjectMapper();
        //List<HashMap<String, TeamManager>> tm_hash = null;
        try {
            tm_hash = objectMapper.readValue(f, new TypeReference<List<HashMap<String, TeamManager>>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (HashMap<String, TeamManager> stringTeamManagerHashMap : tm_hash)
            if (stringTeamManagerHashMap.containsKey(username)) {
                System.out.println(stringTeamManagerHashMap.get(username));
                this.tm = stringTeamManagerHashMap.get(username);
                break;
            }
        teamNameLabel.setText(tm.getTeam().getName());
        cityLabel.setText(tm.getTeam().getCity());
        leagueLabel.setText(tm.getTeam().getLeague());
        descriptionLabel.setText(tm.getTeam().getDescription());

        f = new File(String.valueOf(PathHolder.getPathToResourceFile("user_data/player_agent.json")));
        //List<HashMap<String, PlayerAgent>> pa_hash = null;
        try {
            pa_hash = objectMapper.readValue(f, new TypeReference<List<HashMap<String, PlayerAgent>>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }

        PlayerAgent temp_pa;
        ObservableList<String> pa_name_list = FXCollections.observableArrayList();
        for (HashMap<String, PlayerAgent> pa : pa_hash)
        {
            temp_pa = (PlayerAgent) pa.values().toArray()[0];
            pa_list.add(temp_pa);
            pa_name_list.add(temp_pa.getName());
        }
        paListView.setItems(pa_name_list);
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
    public void checkPlayersButtonPushed(){

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../fxml/tm_check_player_data.fxml"));
        Parent root= null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        TMCheckPlayerData controller;
        controller= loader.getController();
        controller.initData(tm);
        Stage stage = new Stage();
        stage.setTitle("Players");
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.show();

    }
    public void paListClick()
    {
        String pa_name = paListView.getSelectionModel().getSelectedItem();
        PlayerAgent selected_pa = null;
        for(int i=0; i<pa_list.size(); i++) // get player agent by name
            if(pa_list.get(i).getName().equals(pa_name))
                selected_pa = pa_list.get(i);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../fxml/check_player_agent.fxml"));
        Parent root= null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        CheckPlayerAgentController controller;
        controller= loader.getController();
        controller.initData(selected_pa, tm, username, pa_hash, tm_hash);
        Stage stage = (Stage) teamNameLabel.getScene().getWindow();
        //stage.setTitle("Player data");
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.show();
    }
}

