package main;

import java.util.ArrayList;

public class PlayerAgent {
    private String name;
    private ArrayList<Player> players = new ArrayList<Player>();


    public PlayerAgent(String name) {
        this.name = name;
    }

    //getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //add player
    public void addPlayer(Player player)
    {
        this.players.add(player);
    }
}
