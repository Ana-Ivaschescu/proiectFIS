package controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.sun.tools.javac.code.Attribute;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import main.PlayerAgent;
import main.Team;
import main.TeamManager;
import utils.PathHolder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainPAController {


    private String username;
    private PlayerAgent pa;
    private ArrayList<TeamManager> tm_list = new ArrayList<TeamManager>();
    private ObservableList<String> tm_team_list = FXCollections.observableArrayList();

    @FXML
    public Label nameLabel;
    @FXML
    public ListView<String> listView = new ListView<>();

    public void initData(String username)
    {
        this.username = username;
        //load pa
        File f = new File(String.valueOf(PathHolder.getPathToResourceFile("user_data/player_agent.json")));
        //HashMap<String, PlayerAgent> pa_map = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        List<HashMap<String, PlayerAgent>> pa_list = null;
        try {
            pa_list = objectMapper.readValue(f, new TypeReference<List<HashMap<String, PlayerAgent>>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (HashMap<String, PlayerAgent> stringPlayerAgentHashMapHashMap : pa_list)
            if (stringPlayerAgentHashMapHashMap.containsKey(username)) {
                System.out.println(stringPlayerAgentHashMapHashMap.get(username));
                this.pa = stringPlayerAgentHashMapHashMap.get(username);
                break;
            }
        //set data
        System.out.println(pa.getName());
        nameLabel.setText(pa.getName());

        //load tm_list
        f = new File(String.valueOf(PathHolder.getPathToResourceFile("user_data/team_manager.json")));
        List<HashMap<String, TeamManager>> tm_hash = null;
        try {
            tm_hash = objectMapper.readValue(f, new TypeReference<List<HashMap<String, TeamManager>>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        TeamManager temp_tm;
        for (HashMap<String, TeamManager> stringTeamManagerHashMap : tm_hash)
        {
            temp_tm = (TeamManager)stringTeamManagerHashMap.values().toArray()[0];
            tm_list.add(temp_tm);
            tm_team_list.add(temp_tm.getName() + " " + temp_tm.getTeam().getName());
        }
        listView.setItems((tm_team_list));
    }
}