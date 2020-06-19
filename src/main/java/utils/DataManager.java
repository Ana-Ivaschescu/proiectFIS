package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class DataManager {
    private String credentials_path = String.valueOf(PathHolder.getPathToResourceFile("user_data/credentials.json"));
    private String tm_path = String.valueOf(PathHolder.getPathToResourceFile("user_data/team_manager.json"));
    private String pa_path = String.valueOf(PathHolder.getPathToResourceFile("user_data/player_agent.json"));

    public void clearAllData()
    {
        clearCredData();
        clearTMData();
        clearPAData();
    }
    public void clearCredData()
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
    public void clearTMData()
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
    public void clearPAData()
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


}
