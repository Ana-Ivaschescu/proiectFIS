package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

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


}
