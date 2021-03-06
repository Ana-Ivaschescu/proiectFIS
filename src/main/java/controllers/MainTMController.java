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
import utils.DataManager;
import utils.PathHolder;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
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
    private List<HashMap<String, PlayerAgent>> pa_hash_list;
    private List<HashMap<String, TeamManager>> tm_hash_list;

    public void initData(String username)
    {
        this.username = username;
        tm_hash_list = DataManager.readTM();
        for (HashMap<String, TeamManager> stringTeamManagerHashMap : tm_hash_list)
            if (stringTeamManagerHashMap.containsKey(username)) {
                System.out.println(stringTeamManagerHashMap.get(username));
                this.tm = stringTeamManagerHashMap.get(username);
                break;
            }
        teamNameLabel.setText(tm.getTeam().getName());
        cityLabel.setText(tm.getTeam().getCity());
        leagueLabel.setText(tm.getTeam().getLeague());
        descriptionLabel.setText(tm.getTeam().getDescription());

        pa_hash_list = DataManager.readPA();
        //set list
        PlayerAgent temp_pa;
        ObservableList<String> pa_name_list = FXCollections.observableArrayList();
        for (HashMap<String, PlayerAgent> pa : pa_hash_list)
        {
            temp_pa = (PlayerAgent) pa.values().toArray()[0];
            pa_list.add(temp_pa);
            pa_name_list.add(temp_pa.getName());
        }
        paListView.setItems(pa_name_list);
    }

    public void changeTeamDataPushed(){

        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(new URL("file:///" + PathHolder.getPathToResourceFile("/fxml/change_team_data.fxml")));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
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
        try {
            loader.setLocation(new URL("file:///" + PathHolder.getPathToResourceFile("/fxml/tm_check_player_data.fxml")));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
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
        if(selected_pa!=null) {
            FXMLLoader loader = new FXMLLoader();
            try {
                loader.setLocation(new URL("file:///" + PathHolder.getPathToResourceFile("/fxml/check_player_agent.fxml")));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            CheckPlayerAgentController controller;
            controller = loader.getController();
            controller.initData(selected_pa, tm, username, pa_hash_list, tm_hash_list);
            Stage stage = (Stage) teamNameLabel.getScene().getWindow();
            //stage.setTitle("Player data");
            Scene scene = new Scene(root, 800, 600);
            stage.setScene(scene);
            stage.show();
        }
    }
    public void logOutButtonPushed()
    {
        try {
            Stage stage = (Stage) teamNameLabel.getScene().getWindow();
            Parent sign_up_root = FXMLLoader.load(new URL("file:///" + PathHolder.getPathToResourceFile("/fxml/welcome.fxml")));
            Scene scene = new Scene(sign_up_root, 800, 600);
            stage.setScene(scene);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}

