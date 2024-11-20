package upm.controller.command;

import upm.controller.TeamController;

public class TeamRemoveCommand extends Command{
    private final TeamController controller;

    public TeamRemoveCommand(TeamController controller) {
        this.controller = controller;
    }

    @Override
    public String apply(String[] params) {
        String result = super.testparams(params[0], "team-remove", params.length - 1, 1);

        if (result != null && result.isEmpty()) {
            result = controller.removeTeam(params[1]);
        }

        return result;
    }

    @Override
    public String toStringCommand() {
        return "team-remove [playerName]";
    }
}
