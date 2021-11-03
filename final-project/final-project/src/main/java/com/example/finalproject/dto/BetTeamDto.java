package com.example.finalproject.dto;

public class BetTeamDto {
    private long firstTeamAmount;
    private long secondTeamAmount;
    private boolean exception;

    public BetTeamDto() {
    }

    public BetTeamDto(boolean exception) {
        this.exception = exception;
    }

    public boolean isException() {
        return exception;
    }

    public void setException(boolean exception) {
        this.exception = exception;
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
