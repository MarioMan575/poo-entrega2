package upm.model;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String name;
    private String leaderName;
    private List<Player> players;

    public Team(String name, String leaderName) {
        this.name = name;
        this.players = new ArrayList<>();
        this.leaderName = leaderName;


    }

    public String getName() {
        return name;
    }

    public List<Player> getPlayers() {
        return players;
    }
}

