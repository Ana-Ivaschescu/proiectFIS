package controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Player;
import main.PlayerAgent;
import utils.PathHolder;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class EditPlayerController {

    @FXML
    public Label nameLabel;
    @FXML
    public TextField playingPosField;
    @FXML
    public TextField descriptionField;

    private Player p;
    private List<HashMap<String, PlayerAgent>> pa_list;
    private String username;
    public  void initData(Player p,  List<HashMap<String, PlayerAgent>> pa_list, String username)
    {
        if(p!= null) {
            this.p = p;
            nameLabel.setText(p.getName());
            playingPosField.setText(p.getPlaying_position());
            descriptionField.setText(p.getDescription());
        }
        this.username = username;
        this.pa_list = pa_list;
    }

    public void saveButtonPushed()
    {

        String player_pos = playingPosField.getText();
        String player_desc = descriptionField.getText();
        p.setPlaying_position(player_pos);
        p.setDescription(player_desc);

        File f = new File(String.valueOf(PathHolder.getPathToResourceFile("user_data/player_agent.json")));
        //HashMap<String, PlayerAgent> pa_map = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(f, pa_list);
        } catch (IOException e) {
            e.printStackTrace();
        }

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../fxml/player_agent_main.fxml"));
        Parent root= null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MainPAController controller;
        controller= loader.getController();
        controller.initData(username);
        Stage stage = (Stage) playingPosField.getScene().getWindow();
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
    }
}
