/*package upm.controller.command;

import upm.controller.TeamController;

public class TeamDeleteCommand extends Command{
    private final TeamController controller;

    public TeamDeleteCommand(TeamController controller){
        this.controller = controller;
    }

    @Override
    public String apply(String[] params) {
        String result = super.testparams(params[0], "team-delete", params.length - 1, 1);

        if (result != null && result.isEmpty()) {
            result = controller.deleteTeam(params[1]);
        }

        return result;
    }

    @Override
    public String toStringCommand() {
        return "team-delete [teamName]";
    }
}
*/