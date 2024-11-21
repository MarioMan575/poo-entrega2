package upm.controller.command;

import upm.controller.TeamController;
import upm.controller.UserController;

public class TeamAddCommand extends Command {
    private TeamController teamController;
    private UserController userController;

    public TeamAddCommand(TeamController teamController, UserController userController) {
        this.teamController = teamController;
        this.userController = userController;
    }

    @Override
    public String apply(String[] params) {
        String result = super.testparams(params[0], "team-add", params.length - 1, 2);

        if (result != null && result.isEmpty()) {
            if (userController.getLoggedUser() == null) {
                return "Error: You must be logged in to add a player to a team.";
            }

            if (!userController.isAdmin()) {
                return "Error: Only administrators can add players to a team.";
            }

            result = teamController.addTeam(params[1], params[2]);
        }

        return result;
    }

    @Override
    public String toStringCommand() {
        return "team-add [teamName;playerEmail]";
    }
}