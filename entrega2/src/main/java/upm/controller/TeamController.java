package upm.controller;

import upm.model.*;

import java.util.HashMap;

public class TeamController {
    private HashMap<String,User> users;
    private HashMap<String,Team> teams;
    private HashMap<String, Tournament> tournaments;;

    public TeamController(HashMap<String, User> users, HashMap<String,Team> teams, HashMap<String,Tournament> tournaments) {
        this.users = users;
        this.teams = teams;
        this.tournaments = tournaments;
    }

    public String createTeam(String name, User loggedUser) {
        if (teams.containsKey(name)) {
            return "Error: Team with this name already exists.";
        }

        teams.put(name, new Team(name, (Admin) loggedUser));
        return "Success: Team '" + name + "' created successfully by " + loggedUser.getEmail() + ".";
    }

    public String deleteTeam(String teamName) {
        Team team = teams.get(teamName);

        if (team == null) {
            return "Error: Team not found.";
        }

        for (Tournament tournament : tournaments.values()) {
            if (tournament.isInProgress() && tournament.getTeams().containsKey(teamName)) {
                return "Error: Cannot delete team '" + teamName + "' because it is participating in an ongoing tournament.";
            }
        }

        teams.remove(teamName);
        return "Success: Team '" + teamName + "' deleted successfully.";
    }

    public String addTeam(String teamName, String playerEmail) {
        Team team = teams.get(teamName);
        Player player = (Player) users.get(playerEmail);

        if (team == null) {
            return "Error: Team not found.";
        }

        if (player == null) {
            return "Error: Player not found.";
        }

        return team.addPlayer(player);
    }

    public String removeTeam(String teamName, String playerEmail) {
        Team team = teams.get(teamName);
        Player player = (Player) users.get(playerEmail);

        if (team == null) {
            return "Error: Team not found.";
        }

        if (player == null) {
            return "Error: Player not found.";
        }

        return team.removePlayer(player);
    }

}
