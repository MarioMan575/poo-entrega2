package upm.controller.command;

import upm.controller.TournamentController;
import upm.controller.UserController;

public class TournamentListCommand extends Command {

    private TournamentController tournamentController;
    private UserController userController;

    public TournamentListCommand(TournamentController tournamentController, UserController userController) {
        this.tournamentController = tournamentController;
        this.userController = userController;
    }

    @Override
    public String apply(String[] params) {
        String result = super.testparams(params[0], "tournament-list", params.length - 1, 0);

        if (result != null && result.isEmpty()) {
            if (userController.getLoggedUser() == null) {
                result = tournamentController.listTournamentsRandomly();
            } else {
                if (userController.isAdmin()) {
                    tournamentController.removeFinishedTournaments();
                }
                result = tournamentController.listTournamentsByRank();
            }
        }

        return result;
    }

    @Override
    public String toStringCommand() {
        return "tournament-list";
    }
}
