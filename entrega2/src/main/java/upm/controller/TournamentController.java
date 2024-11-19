package upm.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import upm.model.Tournament;

public class TournamentController {
    private List<Tournament> tournaments;

    public TournamentController() {
        this.tournaments = new ArrayList<>();
    }

    public boolean createTournament(String name, Date startDate, Date endDate) {
        for (Tournament tournament : tournaments) {
            if (tournament.getName().equals(name)) {
                return false;
            }
        }
        tournaments.add(new Tournament(name, startDate, endDate));
        return true;
    }

    public boolean deleteTournament(String name) {
        for (Tournament tournament : tournaments) {
            if (tournament.getName().equals(name)) {
                tournaments.remove(tournament);
                return true;
            }
        }
        return false;
    }

    public List<Tournament> getTournaments() {
        return tournaments;
    }
}

