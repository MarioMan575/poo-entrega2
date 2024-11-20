package upm.controller.command;

import upm.controller.TeamController;

public class TeamCreateCommand extends Command {
    private TeamController controller;

    public TeamCreateCommand(TeamController controller) {
        this.controller = controller;
    }

    @Override
    public String apply(String[] params) {
        String result = super.testparams(params[0], "team-create", params.length - 1, 2);

        if (result != null && result.isEmpty()) {
            result = controller.createTeam(params[1], params[2]);
        }

        return result;
    }

    @Override
    public String toStringCommand() {
        return "team-create [name;captain]";
    }
}
