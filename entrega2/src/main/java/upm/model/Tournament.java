package upm.model;

import java.util.Date;
import java.util.HashMap;

public class Tournament {
    private String name;
    private Date startDate;
    private Date endDate;
    private String league;
    private String sport;
    private HashMap<String,Player> players;
    private HashMap<String,Team> teams;

    public Tournament(String name, Date startDate, Date endDate, String league, String sport) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.league = league;
        this.sport = sport;
        this.players = new HashMap<>();
        this.teams = new HashMap<>();
    }

    public String addPlayer(Player player) {
        if (players.containsKey(player.getEmail())) {
            return "Error: Player is already in the tournament.";
        }
        players.put(player.getEmail(), player);
        return "Success: Player added to the tournament.";
    }

    public String addTeam(Team team) {
        if (teams.containsKey(team.getName())) {
            return "Error: Team is already in the tournament.";
        }
        teams.put(team.getName(), team);
        return "Success: Team added to the tournament.";
    }

    public String removePlayer(Player player) {
        if (players.containsKey(player.getEmail())) {
            return "Error: Player is already in the tournament.";
        }
        players.remove(player.getEmail(), player);
        return "Success: Player added to the tournament.";
    }

    public String removeTeam(Team team) {
        if (teams.containsKey(team.getName())) {
            return "Error: Team is already in the tournament.";
        }
        teams.remove(team.getName(), team);
        return "Success: Team added to the tournament.";
    }

    public boolean isInProgress() {
        Date currentDate = new Date();
        return (currentDate.after(startDate) && currentDate.before(endDate));
    }

    public boolean isPlayerInTournament(Player player) {
        return players.containsKey(player.getEmail());
    }

    public boolean isPlayerInTeamInTournament(Player player) {
        for (Team team : teams.values()) {
            if (team.getPlayers().containsKey(player.getEmail())) {
                return true;
            }
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getLeague() {
        return league;
    }

    public String getSport() {
        return sport;
    }

    public HashMap<String, Player> getPlayers() {
        return players;
    }

    public HashMap<String, Team> getTeams() {
        return teams;
    }

    @Override
    public String toString() {
        return "Tournament{" +
                "name='" + name + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", league='" + league + '\'' +
                ", sport='" + sport + '\'' +
                '}';
    }

}

