package upm.controller;

import upm.model.Player;

import java.util.HashMap;
import java.util.Map;

public class UserController {
    private Map<String, Player> players;

    public UserController() {
        this.players = new HashMap<>();
    }

    public boolean registerPlayer(String username, String password, String firstName, String lastName, String dni) {
        if (!players.containsKey(username)) {
            players.put(username, new Player(username, password, firstName, lastName, dni));
            return true;
        }
        return false;
    }

    public Player login(String username, String password) {
        Player player = players.get(username);
        if (player != null && player.getPassword().equals(password)) {
            return player;
        }
        return null;
    }
}

