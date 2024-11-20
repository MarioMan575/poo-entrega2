package upm.controller.command;

import upm.controller.TeamController;

public class TeamAddCommand extends Command {
    private TeamController controller;

    public TeamAddCommand(TeamController controller) {
        this.controller = controller;
    }

    @Override
    public String apply(String[] params) {
        String result = super.testparams(params[0], "team-add", params.length - 1, 2);

        if (result != null && result.isEmpty()) {
            result = controller.addTeam(params[1],params[2]);
        }

        return result;
    }

    @Override
    public String toStringCommand() {
        return "team-add [teamName;playerEmail]";
    }
}
