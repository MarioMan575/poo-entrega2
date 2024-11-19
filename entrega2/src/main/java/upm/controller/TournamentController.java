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

    public void createTournament(String name, Date startDate, Date endDate) {
        Tournament tournament = new Tournament(name, startDate, endDate);
        tournaments.add(tournament);
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

