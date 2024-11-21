package upm.controller.command;

import upm.controller.TournamentController;
import upm.controller.UserController;

public class TournamentRemoveCommand extends Command {
    private TournamentController tournamentController;
    private UserController userController;

    public TournamentRemoveCommand(TournamentController tournamentController, UserController userController) {
        this.tournamentController = tournamentController;
        this.userController = userController;
    }

    @Override
    public String apply(String[] params) {
        String result = super.testparams(params[0], "tournament-remove", params.length - 1, 2);

        if (result != null && result.isEmpty()) {
            if (userController.getLoggedUser() == null) {
                return "Error: You must be logged in to remove a player or team from a tournament.";
            }

            if (!userController.isPlayer()) {
                return "Error: Only players can remove themselves or their teams from a tournament.";
            }

            result = tournamentController.removeTournament(params[1], params[2], userController.getLoggedUser());
        }

        return result;
    }

    @Override
    public String toStringCommand() {
        return "tournament-remove [tournamentName; solo or team]";
    }
}
