package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.awt.*;

public class WelcomePAController {

    private String username;

    @FXML
    public Label usernameLabel;

    public void initData(String username)
    {
        this.username = username;
        usernameLabel.setText(this.username);
    }

}
