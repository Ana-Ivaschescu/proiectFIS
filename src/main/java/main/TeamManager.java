package main;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TeamManager {
    private String name;
    private Team team;
    private ArrayList<Request> request_list = new ArrayList<Request>();

    //constructor
    public TeamManager(){ super(); }
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

    public ArrayList<Request> getRequest_list() {
        return request_list;
    }

    public void setRequest_list(ArrayList<Request> request_list) {
        this.request_list = request_list;
    }
    public void addRequest(Request r)
    {
        request_list.add(r);
    }
}
