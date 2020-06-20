package controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.sun.tools.javac.code.Attribute;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import main.Player;
import main.PlayerAgent;
import main.TeamManager;
import utils.DataManager;
import utils.PathHolder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainPAController {


    private String username;
    private PlayerAgent pa;
    private List<HashMap<String, PlayerAgent>> pa_hash_list = null;
    private List<HashMap<String, TeamManager>> tm_hash_list = null;
    private ArrayList<TeamManager> tm_list = new ArrayList<TeamManager>();
    private ObservableList<String> tm_team_list = FXCollections.observableArrayList();

    @FXML
    public Label nameLabel;
    @FXML
    public ListView<String> listView = new ListView<>();
    @FXML
    public ListView<String> playerListView = new ListView<>();

    public void initData(String username)
    {
        this.username = username;
        //load pa
        pa_hash_list = DataManager.readPA();
        for (HashMap<String, PlayerAgent> stringPlayerAgentHashMapHashMap : pa_hash_list)
            if (stringPlayerAgentHashMapHashMap.containsKey(username)) {
                System.out.println(stringPlayerAgentHashMapHashMap.get(username));
                this.pa = stringPlayerAgentHashMapHashMap.get(username);
                break;
            }
        //set data
        System.out.println(pa.getName());
        nameLabel.setText(pa.getName());

        //load tm_list
        tm_hash_list = DataManager.readTM();
        TeamManager temp_tm;
        for (HashMap<String, TeamManager> stringTeamManagerHashMap : tm_hash_list)
        {
            temp_tm = (TeamManager)stringTeamManagerHashMap.values().toArray()[0];
            tm_list.add(temp_tm);
            tm_team_list.add(temp_tm.getName() + "  |  "  + temp_tm.getTeam().getName());
        }
        listView.setItems((tm_team_list));
        ObservableList<String> player_name_list = FXCollections.observableArrayList();
        for(Player p : pa.getPlayers())
            player_name_list.add(p.getName());
        playerListView.setItems(player_name_list);
    }

    @FXML
    public void mouseClickListView()
    {
        String manager_and_team_name = listView.getSelectionModel().getSelectedItem();
        if(manager_and_team_name!=null) {
            String manager_name = manager_and_team_name.split("  |  ")[0];
            //System.out.println(manager_name);
            TeamManager selected_tm = null;
            for (TeamManager tm : tm_list) {
                if (manager_name.equals(tm.getName()))
                    selected_tm = tm;
            }
            //make and open popup
            if (selected_tm != null) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("../fxml/team_data_popup.fxml"));
                Parent root = null;
                try {
                    root = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                TeamDataPopupController controller;
                controller = loader.getController();
                controller.initData(selected_tm);
                Stage stage = new Stage();
                stage.setTitle("Team data");
                Scene scene = new Scene(root, 800, 600);
                stage.setScene(scene);
                stage.show();
            }
        }
    }
    public void mouseClickPlayerListView()
    {

        String player_name = playerListView.getSelectionModel().getSelectedItem();

        Player selected_p = null;
        for (int i =0; i<pa.getPlayers().size(); i++)
        {
            if(pa.getPlayers().get(i).getName().equals(player_name))
                selected_p = pa.getPlayers().get(i);
        }
        //System.out.println(selected_p.getName());
        //make and open popup
        if(selected_p!=null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../fxml/edit_player_popup.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            EditPlayerController controller;
            controller = loader.getController();
            controller.initData(selected_p, pa_hash_list, username);
            Stage stage = (Stage) nameLabel.getScene().getWindow();
            //stage.setTitle("Player data");
            Scene scene = new Scene(root, 800, 600);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    public void addPlayerButtonPushed()
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../fxml/pa_add_player.fxml"));
        Parent root= null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        AddPlayerController controller;
        controller= loader.getController();
        controller.initData(username);
        Stage stage = (Stage) nameLabel.getScene().getWindow();
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
    }

    public void seeRequestsButtonPushed()
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../fxml/pa_check_requests.fxml"));
        Parent root= null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        PACheckRequests controller;
        controller= loader.getController();
        controller.initData(username,pa, tm_hash_list, pa_hash_list);
        Stage stage = (Stage) nameLabel.getScene().getWindow();
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
    }
    public void logOutButtonPushed()
    {
        try {
            Stage stage = (Stage) nameLabel.getScene().getWindow();
            Parent sign_up_root = FXMLLoader.load(getClass().getResource("../fxml/welcome.fxml"));
            Scene scene = new Scene(sign_up_root, 800, 600);
            stage.setScene(scene);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
