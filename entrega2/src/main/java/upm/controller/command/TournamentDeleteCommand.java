package upm.controller.command;

import upm.controller.TournamentController;
import upm.controller.UserController;

public class TournamentDeleteCommand extends Command {
    private TournamentController tournamentController;
    private UserController userController;

    public TournamentDeleteCommand(TournamentController tournamentController, UserController userController) {
        this.tournamentController = tournamentController;
        this.userController = userController;
    }

    @Override
    public String apply(String[] params) {
        String result = super.testparams(params[0], "tournament-delete", params.length - 1, 1);

        if (result != null && result.isEmpty()) {
            if (userController.getLoggedUser() == null) {
                return "Error: You must be logged in to delete a tournament.";
            }

            if (!userController.isAdmin()) {
                return "Error: Only administrators can delete tournaments.";
            }

            result = tournamentController.deleteTournament(params[1]);
        }

        return result;
    }

    @Override
    public String toStringCommand() {
        return "tournament-delete [tournamentName]";
    }
}
