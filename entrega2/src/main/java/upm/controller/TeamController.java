package upm.controller;

import upm.model.Player;
import upm.model.Team;
import upm.model.User;

import java.util.HashMap;

public class TeamController {
    private HashMap<String, Team> teams;

    public TeamController() {
        teams = new HashMap<>();
    }

    public String createTeam(String nombre, String leaderName) {
        if (teams.containsKey(nombre)){
            return "Error: Team with this name already exits.";
        }
        Team team = new Team(nombre,leaderName);
        teams.put(nombre, team);
        return "Success: Team created successfully.";
    }

    public Team getTeam(String nombre) {
        return teams.get(nombre);
    }

    public boolean deleteTeam(String nombre) {
        return teams.remove(nombre) != null;
    }
    public String addTeam(String teamName,String playerEmail){

        return null;
    }
}
