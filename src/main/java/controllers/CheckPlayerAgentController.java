package controllers;

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
import main.Player;
import main.PlayerAgent;
import main.Request;
import main.TeamManager;
import utils.PathHolder;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class CheckPlayerAgentController {

    @FXML
    public Label paNameLabel;
    @FXML
    public Label requestSentLabel;
    @FXML
    public ListView<String> player_list = new ListView<>();

    private  String username;
    private PlayerAgent pa;
    private List<HashMap<String, PlayerAgent>> pa_hash;
    private List<HashMap<String, TeamManager>> tm_hash;
    private TeamManager tm;
    public void initData(PlayerAgent pa, TeamManager tm, String username, List<HashMap<String, PlayerAgent>> pa_hash, List<HashMap<String, TeamManager>> tm_hash)
    {
        this.username = username;
        this.pa = pa;
        this.tm = tm;
        this.pa_hash = pa_hash;
        this.tm_hash = tm_hash;
        paNameLabel.setText(pa.getName());
        requestSentLabel.setText("");
        ObservableList<String> player_name_pos_av_list = FXCollections.observableArrayList();
        for(Player p : pa.getPlayers())
            player_name_pos_av_list.add(p.getName() + "  |  " + p.getPlaying_position() + "  |  " + p.isAvailable());

        player_list.setItems(player_name_pos_av_list);
    }
    public void backButtonPressed()
    {
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
        Stage stage = (Stage) paNameLabel.getScene().getWindow();
        //stage.setTitle("Player data");
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.show();
    }
    public void requestButtonPressed()
    {
        String player_name = player_list.getSelectionModel().getSelectedItem().split("  |  ")[0];

        System.out.println(player_name);
        Player p = null;
        for(Player temp_p : pa.getPlayers())
            if(player_name.equals(temp_p.getName()))
            {
                p = temp_p;
                break;
            }
        if (player_name !=null && p!= null)
            if(p.isAvailable())
                {
                    Request r = new Request(tm.getName(),pa.getName(),p.getName());
                    tm.getRequest_list().add(r);
                    pa.getRequest_list().add(r);
                    requestSentLabel.setText("Request sent for player: " +player_name);
                }
            else
                requestSentLabel.setText(player_name + " is not available!");

        File f = new File(String.valueOf(PathHolder.getPathToResourceFile("user_data/player_agent.json")));

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(f, pa_hash);
        } catch (IOException e) {
            e.printStackTrace();
        }
        f = new File(String.valueOf(PathHolder.getPathToResourceFile("user_data/team_manager.json")));
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(f, tm_hash);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

