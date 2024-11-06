package upm.model;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String name;
    private List<Player> players;

    public Team(String name) {
        this.name = name;
        this.players = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addPlayer(Player player) {
        if (!players.contains(player)) {
            players.add(player);
        }
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public double getAverageStatistics(String category) {
        double total = 0.0;
        for (Player player : players) {
            total += player.getStatistics().get(category);
        }
        return total / players.size();
    }
}

