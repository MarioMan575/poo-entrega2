package upm.model;

import java.util.HashMap;

public class Team {
    private String name;
    private HashMap<String,Player> players;
    private Admin admin;

    public Team(String name, Admin admin) {
        this.name = name;
        this.players = new HashMap<>();
        this.admin = admin;
    }

    public String addPlayer(Player player) {
        if (players.containsKey(player.getEmail())) {
            return "Error: Player is already in the team.";
        }
        players.put(player.getEmail(), player);
        return "Success: Player added to team '" + name + "' successfully.";
    }

    public String removePlayer(Player player) {
        if (!players.containsKey(player.getEmail())) {
            return "Error: Player is not in the team.";
        }
        players.remove(player.getEmail());
        return "Success: Player removed from team '" + name + "' successfully.";
    }

    public double getTeamStatistics() {
        if (players.isEmpty()) {
            return 0.0;
        }

        double totalProduct = 1.0;
        int totalPlayers = 0;

        for (Player player : players.values()) {
            Double wins = player.getStatistics().get("Torneos Ganados");
            if (wins != null) {
                totalProduct *= wins;
                totalPlayers++;
            }
        }

        if (totalPlayers == 0) {
            return 0.0;
        }

        double mean = Math.pow(totalProduct, 1.0 / totalPlayers);
        return mean;
    }

    public String getName() {
        return name;
    }

    public HashMap<String,Player> getPlayers() {
        return players;
    }

    @Override
    public String toString() {
        return "Team{name='" + name + "', number of players=" + players.size() + ", admin='" + admin.getEmail() + "'}";
    }

}