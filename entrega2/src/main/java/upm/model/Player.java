package upm.model;

import java.util.HashMap;
import java.util.Map;

public class Player extends User {
    private String firstName;
    private String lastName;
    private String dni;
    private Admin admin;
    private Map<String, Double> statistics;

    public Player(String email, String password, String firstName, String lastName, String dni, Admin admin) {
        super(email, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.dni = dni;
        this.admin = admin;

        this.statistics = new HashMap<>();
        statistics.put("Puntos Marcados", 0.0);
        statistics.put("Partidos Ganados", 0.0);
        statistics.put("Puntos de Asistencia", 0.0);
        statistics.put("Torneos Ganados", 0.0);
        statistics.put("Dinero Generado", 0.0);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDni() {
        return dni;
    }

    public Admin getAdmin() {
        return admin;
    }

    public Map<String, Double> getStatistics() {
        return statistics;
    }

    public void updateStatistic(String category, double value) {
        statistics.put(category, value);
    }

    @Override
    public String toString() {
        return "Player{" +
                "email='" + getEmail() + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dni='" + dni + '\'' +
                ", admin=" + admin.getEmail() +
                '}';
    }
}
