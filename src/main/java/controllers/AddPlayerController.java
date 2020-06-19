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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Player;
import main.PlayerAgent;
import utils.DataManager;
import utils.PathHolder;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class AddPlayerController {

    ObservableList<String> positionList = FXCollections.observableArrayList("guard", "wing", "center");
    @FXML
    public TextField playerNameField;
    @FXML
    public ChoiceBox<String> positionBox;
    @FXML
    public TextField playerDescriptionField;

    private String username;
    private PlayerAgent pa;

    @FXML
    private void initialize()
    {
        positionBox.setValue("guard");
        positionBox.setItems(positionList);

    }

    public void initData(String username)
    {
        this.username = username;
    }

    public void saveButtonPushed()
    {
        String player_name = playerNameField.getText();
        String player_pos = String.valueOf(positionBox.getValue());
        String player_desc = playerDescriptionField.getText();

        Player p = new Player(player_name, player_pos, player_desc);

        File f = new File(String.valueOf(PathHolder.getPathToResourceFile("user_data/player_agent.json")));
        //HashMap<String, PlayerAgent> pa_map = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        List<HashMap<String, PlayerAgent>> pa_hash_list = DataManager.readPA();

        for(int i=0; i<pa_hash_list.size(); i++)
            if(pa_hash_list.get(i).containsKey(username)) {
                pa_hash_list.get(i).get(username).addPlayer(p);
                break;
            }
        DataManager.savePA(pa_hash_list);
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

