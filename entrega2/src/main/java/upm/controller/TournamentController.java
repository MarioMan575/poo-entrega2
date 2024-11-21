package upm.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import upm.model.Player;
import upm.model.Team;
import upm.model.Tournament;
import upm.model.User;

public class TournamentController {
    private HashMap<String,User> users;
    private HashMap<String,Team> teams;
    private HashMap<String,Tournament> tournaments;

    public TournamentController(HashMap<String, User> users, HashMap<String,Team> teams, HashMap<String,Tournament> tournaments) {
        this.users = users;
        this.teams = teams;
        this.tournaments = tournaments;
    }

    public String createTournament(String name, String startDateStr, String endDateStr, String league, String sport) {
        if (tournaments.containsKey(name)) {
            return "Error: A tournament with this name already exists.";
        }

        Date startDate, endDate;
        try {
            startDate = parseDate(startDateStr);
            endDate = parseDate(endDateStr);
        } catch (ParseException e) {
            return "Error: Invalid date format. Use 'dd-MM-yyyy'.";
        }

        if (!startDate.before(endDate)) {
            return "Error: The start date must be earlier than the end date.";
        }

        Tournament newTournament = new Tournament(name, startDate, endDate, league, sport);
        tournaments.put(name, newTournament);

        return "Success: Tournament '" + name + "' created successfully.";
    }

    private Date parseDate(String dateStr) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        format.setLenient(false);
        return format.parse(dateStr);
    }

    public String deleteTournament(String tournamentName) {
        if (!tournaments.containsKey(tournamentName)) {
            return "Error: Tournament not found.";
        }

        tournaments.remove(tournamentName);
        return "Success: Tournament '" + tournamentName + "' deleted successfully.";
    }

    public String matchmakingTournament(String tournamentName, String groupType,String method) {
        Tournament tournament = tournaments.get(tournamentName);
        if (tournament == null) {
            return "Error: Tournament not found.";
        }

        if (!tournament.isInProgress()) {
            return "Error: Matchmaking cannot be performed until the tournament is in progress.";
        }

        if (!groupType.equals("solo") && !groupType.equals("team")) {
            return "Error: Invalid group type. Use 'solo' or 'team'.";
        }

        switch (method) {
            case "-m":
                return performManualMatchmaking(tournament, groupType);
            case "-a":
                return performAutomaticMatchmaking(tournament, groupType);
            default:
                return "Error: Invalid matchmaking method. Use '-m' for manual or '-a' for automatic.";
        }
    }

    private String performManualMatchmaking(Tournament tournament, String groupType) {
        StringBuilder result = new StringBuilder();

        if (groupType.equals("solo")) {
            if (tournament.getPlayers().size() % 2 != 0) {
                return "Error: Odd number of players, cannot perform manual matchmaking.";
            }

            result.append("Manual matchmaking for solo players:\n");
            Object[] playersArray = tournament.getPlayers().values().toArray();
            for (int i = 0; i < playersArray.length; i += 2) {
                result.append(((Player) playersArray[i]).getEmail())
                        .append(" vs ")
                        .append(((Player) playersArray[i + 1]).getEmail())
                        .append("\n");
            }
        } else if (groupType.equals("team")) {
            if (tournament.getTeams().size() % 2 != 0) {
                return "Error: Odd number of teams, cannot perform manual matchmaking.";
            }

            result.append("Manual matchmaking for teams:\n");
            Object[] teamsArray = tournament.getTeams().values().toArray();
            for (int i = 0; i < teamsArray.length; i += 2) {
                Team team1 = (Team) teamsArray[i];
                Team team2 = (Team) teamsArray[i + 1];

                if (team1.getPlayers().size() < 2 || team2.getPlayers().size() < 2) {
                    return "Error: Teams must have more than one player to perform matchmaking.";
                }

                result.append(team1.getName())
                        .append(" vs ")
                        .append(team2.getName())
                        .append("\n");
            }
        } else {
            return "Error: Invalid group type. Use 'solo' or 'team'.";
        }

        return result.toString();
    }

    private String performAutomaticMatchmaking(Tournament tournament, String groupType) {
        StringBuilder result = new StringBuilder();

        if (groupType.equals("solo")) {
            if (tournament.getPlayers().size() % 2 != 0) {
                return "Error: Odd number of players, cannot perform automatic matchmaking.";
            }

            result.append("Automatic matchmaking for solo players:\n");
            Object[] playersArray = tournament.getPlayers().values().toArray();
            Random random = new Random();
            for (int i = 0; i < playersArray.length; i++) {
                int j = random.nextInt(playersArray.length);
                Object temp = playersArray[i];
                playersArray[i] = playersArray[j];
                playersArray[j] = temp;
            }

            for (int i = 0; i < playersArray.length; i += 2) {
                result.append(((Player) playersArray[i]).getEmail())
                        .append(" vs ")
                        .append(((Player) playersArray[i + 1]).getEmail())
                        .append("\n");
            }
        } else if (groupType.equals("team")) {
            if (tournament.getTeams().size() % 2 != 0) {
                return "Error: Odd number of teams, cannot perform automatic matchmaking.";
            }

            result.append("Automatic matchmaking for teams:\n");
            Object[] teamsArray = tournament.getTeams().values().toArray();
            Random random = new Random();
            for (int i = 0; i < teamsArray.length; i++) {
                int j = random.nextInt(teamsArray.length);
                Object temp = teamsArray[i];
                teamsArray[i] = teamsArray[j];
                teamsArray[j] = temp;
            }

            for (int i = 0; i < teamsArray.length; i += 2) {
                Team team1 = (Team) teamsArray[i];
                Team team2 = (Team) teamsArray[i + 1];

                if (team1.getPlayers().size() < 2 || team2.getPlayers().size() < 2) {
                    return "Error: Teams must have more than one player to perform matchmaking.";
                }

                result.append(team1.getName())
                        .append(" vs ")
                        .append(team2.getName())
                        .append("\n");
            }
        } else {
            return "Error: Invalid group type. Use 'solo' or 'team'.";
        }

        return result.toString();
    }

    public String addTournament(String tournamentName, String groupType, User loggedUser) {
        Tournament tournament = tournaments.get(tournamentName);
        if (tournament == null) {
            return "Error: Tournament not found.";
        }

        if (tournament.isInProgress()) {
            return "Error: Tournament is in progress.";
        }

        if (groupType.equals("solo")) {
            tournament.addPlayer((Player) loggedUser);
        } else if (groupType.equals("team")){
            for (Team team : teams.values()) {
                if (team.getPlayers().containsKey(loggedUser.getEmail())) {
                    tournament.addTeam(team);
                }
            }
            return "Error: Player doesn't belong to any team.";
        }
        return "Error: Invalid group type. Use 'solo' or 'team'.";
    }

    public String removeTournament(String tournamentName, String groupType, User loggedUser) {
        Tournament tournament = tournaments.get(tournamentName);
        if (tournament == null) {
            return "Error: Tournament not found.";
        }

        if (groupType.equals("solo")) {
            tournament.removePlayer((Player) loggedUser);
        } else if (groupType.equals("team")){
            for (Team team : teams.values()) {
                if (team.getPlayers().containsKey(loggedUser.getEmail())) {
                    tournament.removeTeam(team);
                }
            }
            return "Error: Player doesn't belong to any team.";
        }
        return "Error: Invalid group type. Use 'solo' or 'team'.";
    }

    public String listTournamentsRandomly() {
        StringBuilder result = new StringBuilder("Tournaments List (Random Order):\n");
        for (Tournament tournament : tournaments.values()) {
            result.append("Tournament: ").append(tournament.getName()).append("\n");
            result.append("Players:\n");
            for (Player player : tournament.getPlayers().values()) {
                result.append(player.getEmail()).append("\n");
            }
            result.append("Teams:\n");
            for (Team team : tournament.getTeams().values()) {
                result.append(team.getName()).append("\n");
            }
        }
        return result.toString();
    }

    public String listTournamentsByRank() {
        StringBuilder result = new StringBuilder("Tournaments List (Ordered by Rank):\n");
        for (Tournament tournament : tournaments.values()) {
            result.append("Tournament: ").append(tournament.getName()).append("\n");
            result.append("Players (Ranked):\n");
            tournament.getPlayers().values().stream()
                    .sorted((p1, p2) -> Double.compare(p2.getStatistics().get("Torneos Ganados"), p1.getStatistics().get("Torneos Ganados")))
                    .forEach(player -> result.append(player.getEmail()).append(" - Rank: ").append(player.getStatistics().get("Torneos Ganados")).append("\n"));
            result.append("Teams (Ranked):\n");
            // Lógica para ordenar equipos por ranking
            tournament.getTeams().values().stream()
                    .sorted((t1, t2) -> Double.compare(t2.getTeamStatistics(), t1.getTeamStatistics()))
                    .forEach(team -> result.append(team.getName()).append(" - Rank: ").append(team.getTeamStatistics()).append("\n"));
        }
        return result.toString();
    }

    public void removeFinishedTournaments() {
        tournaments.entrySet().removeIf(entry -> !entry.getValue().isInProgress());
    }
}

