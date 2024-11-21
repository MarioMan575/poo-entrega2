package upm.model;

public class Admin extends User {

    public Admin(String email, String password) {
        super(email, password);
    }

    @Override
    public String toString() {
        return "Admin{username='" + getEmail() + "'}";
    }
}
