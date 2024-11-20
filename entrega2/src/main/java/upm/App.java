package upm;

import upm.controller.UserController;
import upm.controller.TournamentController;
import upm.controller.command.Command;
import upm.controller.command.PlayerCreateCommand;
import upm.view.CLI;

import java.util.LinkedList;
import java.util.List;

public class App {
    private UserController userController;
    private TournamentController tournamentController;
    private List<Command> commandList;
    private CLI cli;

    public App() {
        cli = new CLI();
        userController = new UserController(tournamentController.getTournaments());
        tournamentController = new TournamentController();


        commandList = new LinkedList<>();
        commandList.add(new PlayerCreateCommand(userController));
        // Add other commands
    }

    public static void main(String[] args) {
        App app = new App();
        app.start();
    }

    private void start() {
        boolean exit = false;

        List<String> commandStrings = new LinkedList<>();
        for (Command command : commandList) {
            commandStrings.add(command.toStringCommand());
        }

        while (!exit) {
            String command = cli.getCommand(commandStrings);
            if (command.equals("exit")) {
                exit = true;
            } else {
                String[] commandName = command.split(" ", 2);
                String commandParams = commandName[1].replace("[", "").replace("]", "");
                String[] args = commandParams.split(";");
                StringBuilder output = new StringBuilder();
                for (Command cmd : commandList) {
                    String result = cmd.apply(args);
                    if (result != null) {
                        output.append(result).append("\n");
                    }
                }

                cli.printSalida(output.toString());
            }
        }
    }
}
