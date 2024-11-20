package upm;

import upm.controller.TeamController;
import upm.controller.UserController;
import upm.controller.TournamentController;
import upm.controller.command.*;
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
        tournamentController = new TournamentController();
        userController = new UserController(tournamentController.getTournaments());


        commandList = new LinkedList<>();
        commandList.add(new PlayerCreateCommand(userController));
        commandList.add(new LoginCommand(userController));
        commandList.add(new LogoutCommand(userController));
        commandList.add(new PlayerDeleteCommand(userController));
        commandList.add(new StatisticsShowCommand(userController));
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
                try {
                    String[] parts = command.split(" ", 2);

                    String commandName = parts[0];
                    String[] commandArgs;
                    if (parts.length > 1) {
                        commandArgs = parts[1].replace("[", "").replace("]", "").split(";");
                    } else {
                        commandArgs = new String[0];
                    }

                    String[] fullCommand = new String[commandArgs.length + 1];
                    fullCommand[0] = commandName;
                    System.arraycopy(commandArgs, 0, fullCommand, 1, commandArgs.length);

                    StringBuilder output = new StringBuilder();

                    boolean commandFound = false;
                    for (Command cmd : commandList) {
                        if (cmd.toStringCommand().startsWith(commandName)) {
                            String result = cmd.apply(fullCommand);
                            if (result != null) {
                                output.append(result).append("\n");
                            }
                            commandFound = true;
                            break;
                        }
                    }

                    if (commandFound) {
                        cli.printSalida(output.toString().trim());
                    } else {
                        cli.printSalida("Command not recognized or executed.");
                    }
                } catch (Exception e){
                    cli.printSalida("Error processing command: " + e.getMessage());
                }
            }
        }
    }
}
