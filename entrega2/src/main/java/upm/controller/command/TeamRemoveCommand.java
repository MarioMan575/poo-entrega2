package upm.controller.command;

import upm.controller.TeamController;
import upm.controller.UserController;

public class TeamRemoveCommand extends Command {
    private TeamController teamController;
    private UserController userController;

    public TeamRemoveCommand(TeamController teamController, UserController userController) {
        this.teamController = teamController;
        this.userController = userController;
    }

    @Override
    public String apply(String[] params) {
        String result = super.testparams(params[0], "team-remove", params.length - 1, 1);

        if (result != null && result.isEmpty()) {
            if (userController.getLoggedUser() == null) {
                return "Error: You must be logged in to remove a player from a team.";
            }

            if (!userController.isAdmin()) {
                return "Error: Only players can can remove players.";
            }

            result = teamController.removeTeam(params[1],params[2]);
        }

        return result;
    }

    @Override
    public String toStringCommand() {
        return "team-remove [teamName;playerEmail]";
    }
}