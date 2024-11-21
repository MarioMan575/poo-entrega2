package upm.controller.command;

import upm.controller.TournamentController;
import upm.controller.UserController;

public class TournamentAddCommand extends Command {
    private TournamentController tournamentController;
    private UserController userController;

    public TournamentAddCommand(TournamentController tournamentController, UserController userController) {
        this.tournamentController = tournamentController;
        this.userController = userController;
    }

    @Override
    public String apply(String[] params) {
        String result = super.testparams(params[0], "tournament-add", params.length - 1, 2);

        if (result != null && result.isEmpty()) {
            if (userController.getLoggedUser() == null) {
                return "Error: You must be logged in to add a player or a team to a tournament.";
            }

            if (!userController.isPlayer()) {
                return "Error: Only players can join tournaments or add their teams to a tournament.";
            }

            result = tournamentController.addTournament(params[1], params[2], userController.getLoggedUser());
        }

        return result;
    }

    @Override
    public String toStringCommand() {
        return "tournament-add [tournamentName; solo or team]";
    }
}
