package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import main.TeamManager;

public class TeamDataPopupController {

    @FXML
    public Label managerNameLabel;
    @FXML
    public Label teamNameLabel;
    @FXML
    public Label teamCityLabel;
    @FXML
    public Label teamLeagueLabel;
    @FXML
    public Label teamDescriptionLabel;

    public void initData(TeamManager tm)
    {
        managerNameLabel.setText(tm.getName());
        teamNameLabel.setText(tm.getTeam().getName());
        teamCityLabel.setText(tm.getTeam().getCity());
        teamLeagueLabel.setText(tm.getTeam().getLeague());
        teamDescriptionLabel.setText(tm.getTeam().getDescription());
        //availablePositionsLabel.setText(tm.getTeam().getName());
    }
}
