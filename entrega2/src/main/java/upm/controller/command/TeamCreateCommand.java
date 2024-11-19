package upm.controller.command;

import upm.controller.TeamController;

public class TeamCreateCommand extends Command{
    private final TeamController controller;

    public TeamCreateCommand(TeamController controller){
        this.controller = controller;
    }

    @Override
    public String apply(String[] params) {
        if (params.length != 1) {
            return "Invalid number of arguments.";
        }
        boolean success = controller.createTeam(params[0]);
        if (success) {
            return "Team created successfully.";
        } else {
            return "Team already exists.";
        }
    }

    @Override
    public String toStringCommand() {
        return "team-create [teamName]";
    }
}
