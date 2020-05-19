package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class WelcomeTMController {
    private String username;

    @FXML
    public Label usernameLabel;

    public void initData(String username)
    {
        this.username = username;
        usernameLabel.setText(this.username);
    }

}
