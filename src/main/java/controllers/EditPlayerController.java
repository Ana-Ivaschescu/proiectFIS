package controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Player;
import main.PlayerAgent;
import main.TeamManager;
import utils.DataManager;
import utils.PathHolder;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

public class EditPlayerController {

    ObservableList<String> positionList = FXCollections.observableArrayList("guard", "wing", "center");
    @FXML
    public Label nameLabel;
    @FXML
    public ChoiceBox<String> playingPosField;
    @FXML
    public TextField descriptionField;

    private Player p;
    private List<HashMap<String, PlayerAgent>> pa_hash_list;
    private String username;

    @FXML
    private void initialize()
    {
        //playingPosField.setValue("guard");
        playingPosField.setItems(positionList);

    }
    public  void initData(Player p,  List<HashMap<String, PlayerAgent>> pa_list, String username)
    {
        if(p!= null) {
            this.p = p;
            nameLabel.setText(p.getName());
            playingPosField.setValue(p.getPlaying_position());
            descriptionField.setText(p.getDescription());
        }
        this.username = username;
        this.pa_hash_list = pa_list;
    }

    public void saveButtonPushed()
    {

        String player_pos = String.valueOf(playingPosField.getValue());
        String player_desc = descriptionField.getText();
        p.setPlaying_position(player_pos);
        p.setDescription(player_desc);

        DataManager.savePA(pa_hash_list);

        //update in team manager
        List<HashMap<String, TeamManager>> tm_hash_list = DataManager.readTM();
        TeamManager tm;
        for(int i = 0; i<tm_hash_list.size(); i++)
        {
            tm = (TeamManager) tm_hash_list.get(i).values().toArray()[0];
            for(int j=0; j<tm.getTeam().getPlayers().size(); j++)
                if(tm.getTeam().getPlayers().get(j).getName().equals(p.getName()))
                {
                    tm.getTeam().getPlayers().get(j).setPlaying_position(p.getPlaying_position());
                    tm.getTeam().getPlayers().get(j).setDescription(p.getDescription());
                    break;
                }
        }
        DataManager.saveTM(tm_hash_list);

        Stage stage;
        if(playingPosField.getScene() != null) {
            FXMLLoader loader = new FXMLLoader();
            try {
                loader.setLocation(new URL("file:///" + PathHolder.getPathToResourceFile("/fxml/player_agent_main.fxml")));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            MainPAController controller;
            controller = loader.getController();
            controller.initData(username);
            stage = (Stage) playingPosField.getScene().getWindow();
            Scene scene = new Scene(root, 800, 600);
            stage.setScene(scene);
        }
    }
}
