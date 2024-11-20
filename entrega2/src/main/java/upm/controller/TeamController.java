package upm.controller;

import upm.model.Team;
import upm.model.User;

import java.util.HashMap;

public class TeamController {
    private HashMap<String, Team> teams;

    public TeamController() {
        teams = new HashMap<>();
    }

    public boolean createTeam(String nombre) {
        if (teams.containsKey(nombre)){
            return false;
        }
        Team team = new Team(nombre);
        teams.put(nombre, team);
        return true;
    }

    public Team getTeam(String nombre) {
        return teams.get(nombre);
    }

    public boolean deleteTeam(String nombre) {
        return teams.remove(nombre) != null;
    }
}
