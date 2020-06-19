package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import main.Player;

public class TMCheckReqPlayerData {

    @FXML
    public Label nameLabel;
    @FXML
    public Label posLabel;
    @FXML
    public Label avLabel;
    @FXML
    public Label descLabel;

    public void initData(Player p)
    {
        nameLabel.setText(p.getName());
        posLabel.setText(p.getPlaying_position());
        avLabel.setText(String.valueOf(p.isAvailable()));
        descLabel.setText(p.getDescription());
    }
}
