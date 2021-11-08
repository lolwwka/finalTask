package com.example.finalproject.dto;


public class GotBetDto {
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

    public long getBetValue() {
        return betValue;
    }

    public void setBetValue(long betValue) {
        this.betValue = betValue;
    }
}
