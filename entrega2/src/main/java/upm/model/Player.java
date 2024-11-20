package upm.model;

public class Player extends User {
    private String firstName;
    private String lastName;
    private String dni;
    private Admin admin;

    public Player(String email, String password, String firstName, String lastName, String dni, Admin admin) {
        super(email, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.dni = dni;
        this.admin = admin;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
    public User getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "Player{" +
                "email='" + getEmail() + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dni='" + dni + '\'' +
                ", admin=" + admin.getEmail() +  // Mostrar el email del admin
                '}';
    }
}
