package upm.view;

import java.util.List;
import java.util.Scanner;

public class CLI {
    private Scanner scanner;

    public CLI() {
        this.scanner = new Scanner(System.in);
    }

    public String getCommand(List<String> commands) {
        System.out.println("Available commands:");
        for (String command : commands) {
            System.out.println(command);
        }
        return scanner.nextLine();
    }

    public void printSalida(String message) {
        System.out.println(message);
    }
}

