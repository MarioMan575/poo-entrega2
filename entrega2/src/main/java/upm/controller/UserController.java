package upm.controller;

import java.util.HashMap;
import java.util.Map;

import upm.model.Player;

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
        }else{
            return null;
        }
    }
    public boolean logOut(String username) {
        Player player = players.get(username);
        if (player != null) {
            return true;
        }
        return false;
    }
}

