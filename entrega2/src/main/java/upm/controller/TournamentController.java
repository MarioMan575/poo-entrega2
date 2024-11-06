package upm.controller;

import upm.model.Tournament;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class TournamentController {
    private List<Tournament> tournaments;

    public TournamentController() {
        this.tournaments = new ArrayList<>();
    }

    public void createTournament(String name, Date startDate, Date endDate) {
        Tournament tournament = new Tournament(name, startDate, endDate);
        tournaments.add(tournament);
    }

    public List<Tournament> getTournaments() {
        return tournaments;
    }
}

