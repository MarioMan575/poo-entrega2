package upm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import upm.model.Admin;
import upm.model.Player;
import upm.model.User;
import upm.model.Team;
import upm.model.Tournament;

public class UserController {
    private Map<String, User> users;
    private User loggedUser;
    private ArrayList<Tournament> tournaments;

    public UserController(ArrayList<Tournament> tournaments) {
        this.users = new HashMap<>();
        this.loggedUser = null;
        this.tournaments = tournaments;
    }

    public String registerPlayer(String email, String password, String firstName, String lastName, String dni) {
        if (users.containsKey(email)) {
            return "Error: Player with this email already exists.";
        }

        if (!isAdmin()) {
            return "Error: Only admins can register players.";
        }

        users.put(email, new Player(email, password, firstName, lastName, dni, (Admin) loggedUser));
        return "Success: Player registered successfully.";
    }

    public String registerAdmin(String email, String password) {
        if (users.containsKey(email)) {
            return "Error: Admin with this email already exists.";
        }

        if (!isAdmin()) {
            return "Error: Only admins can register other admins.";
        }

        users.put(email, new Admin(email, password));
        return "Success: Admin registered successfully.";
    }

    public String login(String email, String password) {
        User user = users.get(email);
        if (user != null && user.getPassword().equals(password)) {
            loggedUser = user;
            return "Inicio de sesión exitoso como " + loggedUser.getEmail() + ".";
        }
        return "Error: Usuario o contraseña incorrectos.";
    }


    public String logout() {
        if (loggedUser != null) {
            String email = loggedUser.getEmail();
            loggedUser = null;
            return "Sesión cerrada correctamente para " + email + ".";
        } else {
            return "No hay ningún usuario logueado.";
        }
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public boolean isPlayer() {
        return loggedUser instanceof Player;
    }

    public boolean isAdmin() {
        return loggedUser instanceof Admin;
    }

    public Map<String, Player> listPlayers() {
        return users.values().stream()
                .filter(user -> user instanceof Player)
                .map(user -> (Player) user)
                .collect(Collectors.toMap(Player::getEmail, player -> player));
    }

    public String deletePlayer(String playerEmail) {
        User user = users.get(playerEmail);
        if (user == null || !(user instanceof Player)) {
            return "Error: Player not found.";
        }

        Player player = (Player) user;

        if (isPlayerInTournament(player)) {
            return "Error: Player is participating in an ongoing tournament and cannot be deleted.";
        }

        if (isPlayerInTeamInTournament(player)) {
            return "Error: Player belongs to a team participating in an ongoing tournament and cannot be deleted.";
        }

        users.remove(playerEmail);
        return "Success: Player deleted successfully.";
    }

    private boolean isPlayerInTournament(Player player) {
        for (Tournament tournament : tournaments) {
            if (tournament.isInProgress()) {
                for (Team team : tournament.getTeams()) {
                    if (team.getPlayers().contains(player)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isPlayerInTeamInTournament(Player player) {
        for (Tournament tournament : tournaments) {
            if (tournament.isInProgress()) {
                for (Team team : tournament.getTeams()) {
                    if (team.getPlayers().contains(player)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}

