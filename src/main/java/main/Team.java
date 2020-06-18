package main;

import java.util.ArrayList;

public class Team {
    private String name;
    private String city;
    private String league;
    private String description;
    private ArrayList<Player> players = new ArrayList<Player>();
    //poza echipa

    //constructor
    public Team() {super();}
    public Team(String name, String city, String league, String description) {
        this.name = name;
        this.city = city;
        this.league = league;
        this.description = description;
    }





    //getters
    public String getDescription() {
        return description;
    }
    public String getName() {
        return name;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public String getLeague() {
        return league;
    }

    public String getCity() {
        return city;
    }

    //setters
    public void setDescription(String description) {
        this.description = description;
    }
    public void setLeague(String league) {
        this.league = league;
    }

    public void setCity(String city) {
        this.city = city;
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
