package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import main.*;
import utils.PathHolder;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;


public class PACheckRequests {

    @FXML
    public ListView<String> request_list = new ListView<>();


    private String username;
    private PlayerAgent pa;
    private List<HashMap<String, TeamManager>> tm_hash_list;
    private List<HashMap<String, PlayerAgent>> pa_hash_list;
    private Player p;
    private Request r_pa, r_tm;
    private TeamManager tm;


    public void initData(String username, PlayerAgent pa, List<HashMap<String, TeamManager>> tm_hash_list, List<HashMap<String, PlayerAgent>> pa_hash_list)
    {
        this.username = username;
        this.pa = pa;
        this.tm_hash_list = tm_hash_list;
        this.pa_hash_list = pa_hash_list;

        updateList();
    }

    public void backButtonPressed()
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
        Stage stage = (Stage) request_list.getScene().getWindow();
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
    }

    private void getRequestData()
    {
        String tm_name= request_list.getSelectionModel().getSelectedItem().split("  |  ")[0];
        String p_name= request_list.getSelectionModel().getSelectedItem().split("  |  ")[2];
        String status_name= request_list.getSelectionModel().getSelectedItem().split("  |  ")[4];
        for(int i = 0; i<tm_hash_list.size(); i++)
        {
            tm = (TeamManager) tm_hash_list.get(i).values().toArray()[0];
            if(tm.getName().equals(tm_name))
                break;
            else tm = null;
        }
        for(int i=0; i<pa.getPlayers().size(); i++)
        {
            if(pa.getPlayers().get(i).getName().equals(p_name)) {
                p = pa.getPlayers().get(i);
                break;
            }
        }
        for(int i=0; i<pa.getRequest_list().size(); i++)
        {
            if(pa.getRequest_list().get(i).getTm_name().equals(tm_name) &&
                    pa.getRequest_list().get(i).getP_name().equals(p_name)&&
                    pa.getRequest_list().get(i).getPa_name().equals(pa.getName()))
            {
                r_pa =pa.getRequest_list().get(i);
                break;
            }
        }
        for(int i=0; i<tm.getRequest_list().size(); i++)
        {
            if(tm.getRequest_list().get(i).getTm_name().equals(tm_name) &&
                    tm.getRequest_list().get(i).getP_name().equals(p_name)&&
                    tm.getRequest_list().get(i).getPa_name().equals(pa.getName()))
            {
                r_tm =tm.getRequest_list().get(i);
                break;
            }
        }

    }

    private void saveData()
    {
        File f = new File(String.valueOf(PathHolder.getPathToResourceFile("user_data/player_agent.json")));

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(f, pa_hash_list);
        } catch (IOException e) {
            e.printStackTrace();
        }
        f = new File(String.valueOf(PathHolder.getPathToResourceFile("user_data/team_manager.json")));
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(f, tm_hash_list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void updateList()
    {
        ObservableList<String> request_data = FXCollections.observableArrayList();
        for(Request req : pa.getRequest_list())
        {
            request_data.add(req.getTm_name() +"  |  " + req.getP_name() + "  |  " + req.getStatus());
        }
        request_list.setItems(request_data);
    }

    public void acceptButtonPressed()
    {
        this.getRequestData();
        if(r_pa.getStatus().equals("pending"))
        {
            r_pa.setStatus("accept");
            r_tm.setStatus("accept");
            p.setAvailable(false);
            tm.getTeam().addPlayer(p);
            saveData();
            updateList();
        }

    }
    public void denyButtonPressed()
    {
        this.getRequestData();
        if(r_pa.getStatus().equals("pending")) {
            r_pa.setStatus("deny");
            r_tm.setStatus("deny");
            updateList();
        }
        saveData();
    }

}