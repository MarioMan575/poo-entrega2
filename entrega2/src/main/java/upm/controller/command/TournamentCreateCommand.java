package upm.controller.command;

import upm.controller.TournamentController;

public class TournamentCreateCommand extends Command{
    private final TournamentController controller;

    public TournamentCreateCommand(TournamentController controller) {
        this.controller = controller;
    }

    @Override
    public String apply(String[] params) {
        if (params.length != 3) {
            return "Invalid number of arguments.";
        }
        boolean success = controller.createTournament(params[0], params[1], params[2]); //Queda arreglar aquí para que se pase un Date en vez de un String
        if (success) {
            return "Tournament created successfully.";
        } else {
            return "Tournament already exists.";
        }
    }

    @Override
    public String toStringCommand() {
        return "tournament-create [tournamentName]";
    }
}
