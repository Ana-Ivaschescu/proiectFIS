package controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Player;
import main.PlayerAgent;
import utils.PathHolder;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class AddPlayerController {
    @FXML
    public TextField playerNameField;
    @FXML
    public TextField playerPositionField;
    @FXML
    public TextField playerDescriptionField;

    private String username;
    private PlayerAgent pa;

    public void initData(String username)
    {
        this.username = username;
    }

    public void saveButtonPushed()
    {
        String player_name = playerNameField.getText();
        String player_pos = playerPositionField.getText();
        String player_desc = playerDescriptionField.getText();

        Player p = new Player(player_name, player_pos, player_desc);

        File f = new File(String.valueOf(PathHolder.getPathToResourceFile("user_data/player_agent.json")));
        //HashMap<String, PlayerAgent> pa_map = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        List<HashMap<String, PlayerAgent>> pa_list = null;
        try {
            pa_list = objectMapper.readValue(f, new TypeReference<List<HashMap<String, PlayerAgent>>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(int i=0; i<pa_list.size(); i++)
            if(pa_list.get(i).containsKey(username)) {
                pa_list.get(i).get(username).addPlayer(p);
                break;
            }

        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(f, pa_list);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //change scene back
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
        Stage stage = (Stage) playerNameField.getScene().getWindow();
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
    }


    public void backButtonPushed()
    {
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
        Stage stage = (Stage) playerNameField.getScene().getWindow();
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
    }
}

