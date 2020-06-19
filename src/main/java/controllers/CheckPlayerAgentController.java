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
    private List<HashMap<String, PlayerAgent>> pa_hash_list;
    private List<HashMap<String, TeamManager>> tm_hash_list;
    private TeamManager tm;
    public void initData(PlayerAgent pa, TeamManager tm, String username, List<HashMap<String, PlayerAgent>> pa_hash, List<HashMap<String, TeamManager>> tm_hash)
    {
        this.username = username;
        this.pa = pa;
        this.tm = tm;
        this.pa_hash_list = pa_hash;
        this.tm_hash_list = tm_hash;
        paNameLabel.setText(pa.getName());
        requestSentLabel.setText("");
        ObservableList<String> player_name_pos_av_list = FXCollections.observableArrayList();
        for(Player p : pa.getPlayers())
            player_name_pos_av_list.add(p.getName() + "  |  " + p.getPlaying_position() + "  |  " + p.isAvailable());

        player_list.setItems(player_name_pos_av_list);
    }
    public PlayerAgent getPa() {
        return pa;
    }

    public void setPa(PlayerAgent pa) {
        this.pa = pa;
    }

    public TeamManager getTm() {
        return tm;
    }

    public void setTm(TeamManager tm) {
        this.tm = tm;
    }

    public void setPa_hash_list(List<HashMap<String, PlayerAgent>> pa_hash_list) {
        this.pa_hash_list = pa_hash_list;
    }

    public void setTm_hash_list(List<HashMap<String, TeamManager>> tm_hash_list) {
        this.tm_hash_list = tm_hash_list;
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
    public void checkPlayerDataButtonPressed()
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

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../fxml/tm_check_req_player_data.fxml"));
        Parent root= null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        TMCheckReqPlayerData controller;
        controller= loader.getController();
        controller.initData(p);
        Stage stage = new Stage();
        //stage.setTitle("Player data");
        Scene scene = new Scene(root, 500, 300);
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
        boolean already_req = false;
        if (player_name !=null && p!= null)
            if(p.isAvailable()) {
                for (Request req : tm.getRequest_list())
                    if (req.getP_name().equals(player_name)) {
                        requestSentLabel.setText(player_name + " already requested!");
                        already_req = true;
                        break;

                    }
                if (!already_req) {
                    Request r = new Request(tm.getName(), pa.getName(), p.getName());
                    tm.getRequest_list().add(r);
                    pa.getRequest_list().add(r);
                    requestSentLabel.setText("Request sent for player: " + player_name);
                }
            }
            else
                requestSentLabel.setText(player_name + " is not available!");

        File f = new File(String.valueOf(PathHolder.getPathToResourceFile("user_data/player_agent.json")));

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(f, pa_hash_list);
        } catch (IOException e) {
            e.printStackTrace();
        }
        f = new File(String.valueOf(PathHolder.getPathToResourceFile("user_data/team_manager.json")));
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(f, tm_hash_list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

