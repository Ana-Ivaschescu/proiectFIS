package main;

import java.util.ArrayList;

public class Team {
    private String name;
    private String city;
    private String league;
    private ArrayList<Player> players = new ArrayList<Player>();
    //poza echipa

    //constructor
    public Team(String name, String city, String league) {
        this.name = name;
        this.city = city;
        this.league = league;
    }
    //getters
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
