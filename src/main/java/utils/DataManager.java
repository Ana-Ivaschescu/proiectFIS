package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import main.Player;
import main.PlayerAgent;
import main.TeamManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

public class DataManager {
    private static String credentials_path = String.valueOf(PathHolder.getPathToResourceFile("user_data/credentials.json"));
    private static String tm_path = String.valueOf(PathHolder.getPathToResourceFile("user_data/team_manager.json"));
    private static String pa_path = String.valueOf(PathHolder.getPathToResourceFile("user_data/player_agent.json"));

    public static void clearAllData()
    {
        clearCredData();
        clearTMData();
        clearPAData();
    }
    public static void clearCredData()
    {
        File f = new File(credentials_path);
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        writer.print("[]");
        writer.close();
    }
    public static void clearTMData()
    {
        File f = new File(tm_path);
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        writer.print("[]");
        writer.close();

    }
    public static void clearPAData()
    {
        File f = new File(pa_path);
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        writer.print("[]");
        writer.close();
    }

    public static List<HashMap<String, TeamManager>> readTM()
    {
        List<HashMap<String, TeamManager>> tm_hash_list = null;
        File f = new File(String.valueOf(PathHolder.getPathToResourceFile("user_data/team_manager.json")));
        //HashMap<String, PlayerAgent> pa_map = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        //List<HashMap<String, PlayerAgent>> pa_list = null;
        try {
            tm_hash_list = objectMapper.readValue(f, new TypeReference<List<HashMap<String, TeamManager>>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tm_hash_list;
    }
    public static List<HashMap<String, PlayerAgent>> readPA()
    {
        List<HashMap<String, PlayerAgent>> pa_hash_list = null;

        File f = new File(String.valueOf(PathHolder.getPathToResourceFile("user_data/player_agent.json")));
        //HashMap<String, PlayerAgent> pa_map = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        //List<HashMap<String, PlayerAgent>> pa_list = null;
        try {
            pa_hash_list = objectMapper.readValue(f, new TypeReference<List<HashMap<String, PlayerAgent>>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }

        return pa_hash_list;
    }
    public static PlayerAgent searchPA(String pa_name, List<HashMap<String, PlayerAgent>> pa_hash_list)
    {
        PlayerAgent pa = null;
        for(int i = 0; i<pa_hash_list.size(); i++)
        {
            pa = (PlayerAgent) pa_hash_list.get(i).values().toArray()[0];
            if(pa.getName().equals(pa_name))
                break;
            else pa = null;
        }
        return pa;
    }
    public static TeamManager searchTM(String tm_name, List<HashMap<String, TeamManager>> tm_hash_list)
    {

        TeamManager tm = null;
        for(int i = 0; i<tm_hash_list.size(); i++)
        {
            tm = (TeamManager) tm_hash_list.get(i).values().toArray()[0];
            if(tm.getName().equals(tm_name))
                break;
            else tm = null;
        }
        return tm;
    }

    public static void saveTM(List<HashMap<String, TeamManager>> tm_hash_list)
    {
        File f = new File(String.valueOf(PathHolder.getPathToResourceFile("user_data/team_manager.json")));
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(f, tm_hash_list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void savePA(List<HashMap<String, PlayerAgent>> pa_hash_list)
    {
        File f = new File(String.valueOf(PathHolder.getPathToResourceFile("user_data/player_agent.json")));
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(f, pa_hash_list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void saveAll(List<HashMap<String, TeamManager>> tm_hash_list, List<HashMap<String, PlayerAgent>> pa_hash_list)
    {
        saveTM(tm_hash_list);
        savePA(pa_hash_list);
    }

}
