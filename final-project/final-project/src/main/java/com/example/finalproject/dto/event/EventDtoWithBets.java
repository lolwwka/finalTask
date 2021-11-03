package com.example.finalproject.dto.event;

public class EventDtoWithBets {
    private String firstTeam;
    private String secondTeam;
    private long firstTeamAmount;
    private long secondTeamAmount;
    private String tournament;

    public String getTournament() {
        return tournament;
    }

    public void setTournament(String tournament) {
        this.tournament = tournament;
    }

    public String getFirstTeam() {
        return firstTeam;
    }

    public void setFirstTeam(String firstTeam) {
        this.firstTeam = firstTeam;
    }

    public String getSecondTeam() {
        return secondTeam;
    }

    public void setSecondTeam(String secondTeam) {
        this.secondTeam = secondTeam;
    }

    public long getFirstTeamAmount() {
        return firstTeamAmount;
    }

    public void setFirstTeamAmount(long firstTeamAmount) {
        this.firstTeamAmount = firstTeamAmount;
    }

    public long getSecondTeamAmount() {
        return secondTeamAmount;
    }

    public void setSecondTeamAmount(long secondTeamAmount) {
        this.secondTeamAmount = secondTeamAmount;
    }
}
