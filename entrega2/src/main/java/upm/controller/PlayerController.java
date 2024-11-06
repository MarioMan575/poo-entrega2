package upm.controller;

import upm.model.Player;
import upm.model.Admin;
import java.util.HashMap;

public class PlayerController {
    private HashMap<String, Player> players;

    public PlayerController() {
        players = new HashMap<>();
    }

    public boolean createPlayer(String nombre, String apellidos, String dni, String username, String password, Admin admin) {
        if (players.containsKey(dni)) {
            return false;
        }
        Player player = new Player(nombre, apellidos, dni, username, password);
        players.put(dni, player);
        return true;
    }

    public boolean deletePlayer(String dni) {
        return players.remove(dni) != null;
    }

    public Player getPlayer(String dni) {
        return players.get(dni);
    }
}
