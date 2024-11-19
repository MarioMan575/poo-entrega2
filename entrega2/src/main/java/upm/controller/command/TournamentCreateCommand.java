package upm.controller.command;

import java.util.Date;

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
        Date startDate = new Date();
        Date endDate = new Date();
        startDate.setTime(Long.parseLong(params[1]));
        endDate.setTime(Long.parseLong(params[2]));
        boolean success = controller.createTournament(params[0], startDate, endDate);
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
