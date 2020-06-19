package main;

import java.util.ArrayList;
import java.util.HashMap;

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
        if(pos.equals("guard") && nr_guards < nr_guards_max)
        {
            nr_guards++;
            players.add(p);
            p.setAvailable(false);
        }
        if(pos.equals("wing") && nr_wings < nr_wings_max)
        {
            nr_guards++;
            players.add(p);
            p.setAvailable(false);
        }
        if(pos.equals("center") && nr_centers < nr_centers_max)
        {
            nr_guards++;
            players.add(p);
            p.setAvailable(false);
        }
    }
    public HashMap<String, Integer> playerCount()
    {
        HashMap<String, Integer> playercount = new HashMap<>();
        playercount.put("total", 0);
        playercount.put("guards", 0);
        playercount.put("wings", 0);
        playercount.put("centers", 0);
        for(Player p: this.players)
        {
            playercount.put("total", playercount.get("total")+1);
            if(p.getPlaying_position().equals("guard"))
                playercount.put("guards", playercount.get("guards")+1);
            else if(p.getPlaying_position().equals("wing"))
            playercount.put("wings", playercount.get("wings")+1);
            else if(p.getPlaying_position().equals("center"))
            playercount.put("centers", playercount.get("centers")+1);
        }
        return playercount;

    }





}
