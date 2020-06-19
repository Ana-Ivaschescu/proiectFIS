package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import main.Player;
import main.TeamManager;

import java.util.HashMap;

public class TMCheckPlayerData {

    @FXML
    public Label teamNameLabel;
    @FXML
    public ListView<String> player_list = new ListView<>();
    @FXML
    public Label noPlayersLabel;
    @FXML
    public Label noGuardsLabel;
    @FXML
    public Label noWingsLabel;
    @FXML
    public Label noCentersLabel;

    public void initData(TeamManager tm)
    {
        teamNameLabel.setText(tm.getTeam().getName());

        ObservableList<String> player_data  = FXCollections.observableArrayList();
        for(Player p : tm.getTeam().getPlayers())
        {
            player_data.add(p.getName() + "  |  " + p.getPlaying_position() + "  |  " + p.getDescription());
        }
        player_list.setItems(player_data);

        HashMap<String, Integer> player_count = tm.getTeam().playerCount();
        noPlayersLabel.setText(String.valueOf(player_count.get("total")));
        noGuardsLabel.setText(String.valueOf(player_count.get("guards")));
        noCentersLabel.setText(String.valueOf(player_count.get("centers")));
        noWingsLabel.setText(String.valueOf(player_count.get("wings")));
    }


}
