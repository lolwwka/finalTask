package com.example.finalproject.dto;

public class BetEventDto {
    private String firstTeam;
    private String secondTeam;
    private String tournament;
    private long betAmount;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(long betAmount) {
        this.betAmount = betAmount;
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

    public String getTournament() {
        return tournament;
    }

    public void setTournament(String tournament) {
        this.tournament = tournament;
    }
}
