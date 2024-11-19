package upm.controller.command;

import upm.controller.TournamentController;

public class TournamentDeleteCommand extends Command{
    private final TournamentController controller;

    public TournamentDeleteCommand(TournamentController controller) {
        this.controller = controller;
    }

    @Override
    public String apply(String[] params) {
        if (params.length != 1) {
            return "Invalid number of arguments.";
        }
        boolean success = controller.deleteTournament(params[0]);
        if (success) {
            return "Tournament deleted successfully.";
        } else {
            return "Tournament not found.";
        }
    }

    @Override
    public String toStringCommand() {
        return "tournament-delete [tournamentName]";
    }
}
