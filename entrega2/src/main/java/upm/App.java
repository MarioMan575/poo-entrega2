package upm;

import upm.controller.TeamController;
import upm.controller.UserController;
import upm.controller.TournamentController;
import upm.controller.command.*;
import upm.model.Team;
import upm.model.Tournament;
import upm.model.User;
import upm.view.CLI;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class App {
    private UserController userController;
    private TeamController teamController;
    private TournamentController tournamentController;
    private List<Command> commandList;
    private CLI cli;

    public App() {
        cli = new CLI();
        HashMap<String, User> users = new HashMap<>();
        HashMap<String, Team> teams = new HashMap<>();
        HashMap<String, Tournament> tournaments = new HashMap<>();
        userController = new UserController(users,teams,tournaments);
        teamController = new TeamController(users,teams,tournaments);
        tournamentController = new TournamentController(users,teams,tournaments);


        commandList = new LinkedList<>();
        commandList.add(new PlayerCreateCommand(userController));
        commandList.add(new TeamCreateCommand(teamController,userController));
        commandList.add(new PlayerDeleteCommand(userController));
        commandList.add(new TeamDeleteCommand(teamController,userController));
        commandList.add(new TeamAddCommand(teamController,userController));
        commandList.add(new TeamRemoveCommand(teamController,userController));
        commandList.add(new TournamentCreateCommand(tournamentController,userController));
        commandList.add(new TournamentDeleteCommand(tournamentController,userController));
        commandList.add(new TournamentMatchmakingCommand(tournamentController,userController));
        commandList.add(new TournamentAddCommand(tournamentController,userController));
        commandList.add(new TournamentRemoveCommand(tournamentController,userController));
        commandList.add(new StatisticsShowCommand(userController));
        commandList.add(new LoginCommand(userController));
        commandList.add(new LogoutCommand(userController));
        commandList.add(new TournamentListCommand(tournamentController,userController));
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
                } catch (Exception e) {
                    cli.printSalida("Error processing command: " + e.getMessage());
                }
            }
        }
    }
}
