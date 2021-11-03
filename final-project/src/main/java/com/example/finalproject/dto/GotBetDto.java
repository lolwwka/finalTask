package com.example.finalproject.dto;

public class GotBetDto {
    private String userMail;
    private long betValue;
    private long eventId;
    private String teamName;

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public String getBetTeam() {
        return betTeam;
    }

    public void setBetTeam(String betTeam) {
        this.betTeam = betTeam;
    }

    private String betTeam;

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public long getBetValue() {
        return betValue;
    }

    public void setBetValue(long betValue) {
        this.betValue = betValue;
    }
}
