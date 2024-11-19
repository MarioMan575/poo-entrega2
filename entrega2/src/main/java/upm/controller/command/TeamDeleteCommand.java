package upm.controller.command;

import upm.controller.TeamController;

public class TeamDeleteCommand extends Command{
    private final TeamController controller;

    public TeamDeleteCommand(TeamController controller){
        this.controller = controller;
    }

    @Override
    public String apply(String[] params) {
        if (params.length != 1) {
            return "Invalid number of arguments.";
        }
        boolean success = controller.deleteTeam(params[0]);
        if (success) {
            return "Team deleted successfully.";
        } else {
            return "Team does not exist.";
        }
    }

    @Override
    public String toStringCommand() {
        return "team-delete [teamName]";
    }
}
