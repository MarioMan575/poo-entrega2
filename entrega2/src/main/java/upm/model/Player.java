package upm.model;

import java.util.HashMap;
import java.util.Map;

public class Player extends User {
    private String firstName;
    private String lastName;
    private String dni;
    private Map<String, Double> statistics;

    public Player(String username, String password, String firstName, String lastName, String dni) {
        super(username, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.dni = dni;
        this.statistics = new HashMap<>();
        // Initializing default statistics
        statistics.put("puntos marcados", 0.0);
        statistics.put("partidos ganados", 0.0);
        statistics.put("puntos de asistencia", 0.0);
        statistics.put("torneos ganados", 0.0);
        statistics.put("dinero generado", 0.0);
    }

    // Getters and setters
    public String getDni() {
        return dni;
    }

    public Map<String, Double> getStatistics() {
        return statistics;
    }

    public void updateStatistic(String category, double value) {
        statistics.put(category, value);
    }
}

