package upm.controller.command;

import upm.controller.TournamentController;
import upm.controller.UserController;

public class TournamentMatchmakingCommand extends Command {

    private TournamentController tournamentController;
    private UserController userController;

    public TournamentMatchmakingCommand(TournamentController tournamentController, UserController userController) {
        this.tournamentController = tournamentController;
        this.userController = userController;
    }

    @Override
    public String apply(String[] params) {
        String result = super.testparams(params[0], "tournament-matchmaking", params.length - 1, 2);

        if (result != null && result.isEmpty()) {
            if (userController.getLoggedUser() == null) {
                return "Error: You must be logged in to manage tournament matchmaking.";
            }

            if (!userController.isAdmin()) {
                return "Error: Only administrators can perform tournament matchmaking.";
            }

            result = tournamentController.matchmakingTournament(params[1], params[2],params[3]);
        }

        return result;
    }

    @Override
    public String toStringCommand() {
        return "tournament-matchmaking [tournamentName;solo or team;-m or -a]";
    }
}
