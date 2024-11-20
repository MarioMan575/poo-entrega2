package upm.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Tournament {
    private String name;
    private Date startDate;
    private Date endDate;
    private List<Player> players;
    private List<Team> teams;
    private boolean inProgress;

    public Tournament(String name, Date startDate, Date endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.players = new ArrayList<>();
        this.teams = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        if (!players.contains(player)) {
            players.add(player);
        }
    }

    public void addTeam(Team team) {
        if (!teams.contains(team)) {
            teams.add(team);
        }
    }

    public String getName() {
        return name;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public boolean isInProgress() {
        return inProgress;
    }
}

