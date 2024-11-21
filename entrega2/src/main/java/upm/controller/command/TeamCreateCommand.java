package upm.controller.command;

import upm.controller.TeamController;
import upm.controller.UserController;
import upm.model.Admin;

public class TeamCreateCommand extends Command {
    private TeamController teamController;
    private UserController userController;

    public TeamCreateCommand(TeamController teamController, UserController userController) {
        this.teamController = teamController;
        this.userController = userController;
    }

    @Override
    public String apply(String[] params) {
        String result = super.testparams(params[0], "team-create", params.length - 1, 1);

        if (result != null && result.isEmpty()) {
            if (userController.getLoggedUser() == null) {
                return "Error: You must be logged in to create a team.";
            }

            if (!userController.isAdmin()) {
                return "Error: Only administrators can create teams.";
            }

            result = teamController.createTeam(params[1], userController.getLoggedUser());
        }

        return result;
    }

    @Override
    public String toStringCommand() {
        return "team-create [teamName]";
    }
}