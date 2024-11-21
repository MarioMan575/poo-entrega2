package upm.controller.command;

import upm.controller.TeamController;
import upm.controller.UserController;

public class TeamDeleteCommand extends Command {
    private TeamController teamController;
    private UserController userController;

    public TeamDeleteCommand(TeamController teamController, UserController userController) {
        this.teamController = teamController;
        this.userController = userController;
    }

    @Override
    public String apply(String[] params) {
        String result = super.testparams(params[0], "team-delete", params.length - 1, 1);

        if (result != null && result.isEmpty()) {
            if (userController.getLoggedUser() == null) {
                return "Error: You must be logged in to delete a team.";
            }

            if (!userController.isAdmin()) {
                return "Error: Only administrators can delete teams.";
            }

            result = teamController.deleteTeam(params[1]);
        }

        return result;
    }

    @Override
    public String toStringCommand() {
        return "team-delete [teamName]";
    }
}