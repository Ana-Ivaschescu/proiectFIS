package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import main.Player;
import main.TeamManager;

public class TMCheckPlayerData {

    @FXML
    public Label teamNameLabel;
    @FXML
    public ListView<String> player_list = new ListView<>();

    public void initData(TeamManager tm)
    {
        teamNameLabel.setText(tm.getTeam().getName());

        ObservableList<String> player_data  = FXCollections.observableArrayList();
        for(Player p : tm.getTeam().getPlayers())
        {
            player_data.add(p.getName() + "  |  " + p.getPlaying_position() + "  |  " + p.getDescription());
        }
        player_list.setItems(player_data);
    }
}
