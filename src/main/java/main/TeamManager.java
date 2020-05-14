package main;

public class TeamManager {
    private String name;
    private Team team;

    //constructor
    public TeamManager(String name) {
        this.name = name;
    }

    //setters and getters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
