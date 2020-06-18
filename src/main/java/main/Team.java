package main;

import java.util.ArrayList;

public class Team {
    private String name;
    private String city;
    private String league;
    private String description;
    private ArrayList<Player> players = new ArrayList<Player>();
    //poza echipa
    private int nr_guards = 0;
    private int nr_centers = 0;
    private  int nr_wings = 0;
    private int nr_guards_max = 5, nr_wings_max = 5, nr_centers_max = 5;

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
    public void addPlayer(Player p)
    {
        String pos = p.getPlaying_position();
        if(pos == "guard" && nr_guards < nr_guards_max)
        {
            nr_guards++;
            players.add(p);
            p.setAvailable(false);
        }
        if(pos == "wing" && nr_wings < nr_wings_max)
        {
            nr_guards++;
            players.add(p);
            p.setAvailable(false);
        }
        if(pos == "center" && nr_centers < nr_centers_max)
        {
            nr_guards++;
            players.add(p);
            p.setAvailable(false);
        }
    }





}
