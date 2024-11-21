package upm.controller.command;

import java.util.Date;

import upm.controller.TournamentController;
import upm.controller.UserController;

public class TournamentCreateCommand extends Command {
    private TournamentController tournamentController;
    private UserController userController;

    public TournamentCreateCommand(TournamentController tournamentController, UserController userController) {
        this.tournamentController = tournamentController;
        this.userController = userController;
    }

    @Override
    public String apply(String[] params) {
        String result = super.testparams(params[0], "tournament-create", params.length - 1, 5);

        if (result != null && result.isEmpty()) {
            if (userController.getLoggedUser() == null) {
                return "Error: You must be logged in to create a tournament.";
            }

            if (!userController.isAdmin()) {
                return "Error: Only administrators can create tournaments.";
            }

            result = tournamentController.createTournament(params[1], params[2], params[3], params[4], params[5]);
        }

        return result;
    }

    @Override
    public String toStringCommand() {
        return "tournament-create [tournamentName;startDate;endDate;league;sport]";
    }
}
