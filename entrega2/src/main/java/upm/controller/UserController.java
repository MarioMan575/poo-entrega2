package upm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import upm.model.Admin;
import upm.model.Player;
import upm.model.User;
import upm.model.Team;
import upm.model.Tournament;

public class UserController {
    private HashMap<String, User> users;
    private User loggedUser;
    private ArrayList<Tournament> tournaments;

    public UserController(ArrayList<Tournament> tournaments) {
        this.users = new HashMap<>();
        this.loggedUser = null;
        this.tournaments = tournaments;
        users.put("alvaro@upm.es",new Admin("alvaro@upm.es","1234"));
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
        if (loggedUser != null) {
            return "Error: There is already an active session. Please log out first.";
        }

        User user = users.get(email);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                loggedUser = user; // Iniciar sesión
                return "Login successful as " + loggedUser.getEmail() + ".";
            } else {
                return "Error: Incorrect password.";
            }
        }
        return "Error: Incorrect username or password.";
    }

    public String logout() {
        if (loggedUser != null) {
            String email = loggedUser.getEmail();
            loggedUser = null;
            return "Logout successful for " + email + ".";
        } else {
            return "No user is currently logged in.";
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

    public String deletePlayer(String playerEmail) {
        if (loggedUser == null) {
            return "Error: You must be logged in to delete a player.";
        }

        if (!isAdmin()) {
            return "Error: Only admins can delete players.";
        }

        User user = users.get(playerEmail);
        if (user == null || !isPlayer()) {
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

    public String statisticsShow(String format) {
        // Verificar si el usuario está logueado y si es un jugador
        if (loggedUser == null || !isPlayer()) {
            return "Error: You must be logged in as a player to view statistics.";
        }

        Player player = (Player) loggedUser;

        // Recoger las estadísticas del jugador
        Map<String, Double> statistics = player.getStatistics();

        // Si el formato es CSV
        if (format.equals("-csv")) {
            StringBuilder csv = new StringBuilder();
            csv.append("Category,Value\n");
            for (Map.Entry<String, Double> entry : statistics.entrySet()) {
                csv.append(entry.getKey()).append(",").append(entry.getValue()).append("\n");
            }
            return csv.toString();
        }

        // Si el formato es JSON
        else if (format.equals("-json")) {
            StringBuilder json = new StringBuilder();
            json.append("{\n");

            // Iterar sobre las estadísticas para formar el JSON manualmente
            for (Map.Entry<String, Double> entry : statistics.entrySet()) {
                json.append("  \"").append(entry.getKey()).append("\": ").append(entry.getValue());
                json.append(",\n");
            }

            // Eliminar la última coma y salto de línea
            if (json.length() > 2) {
                json.setLength(json.length() - 2);  // Eliminar la última coma
                json.append("\n");
            }

            json.append("}");

            return json.toString();
        }

        // Si el formato no es válido
        return "Error: Invalid format. Please use '-csv' or '-json'.";
    }

}

