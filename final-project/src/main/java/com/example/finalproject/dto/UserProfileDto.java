package com.example.finalproject.dto;

import java.util.List;

public class UserProfileDto {
    private long balance;
    private List<BetEventDto> bets;

    public UserProfileDto() {
    }

    public UserProfileDto(long balance, List<BetEventDto> bets) {
        this.balance = balance;
        this.bets = bets;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public List<BetEventDto> getBets() {
        return bets;
    }

    public void setBets(List<BetEventDto> bets) {
        this.bets = bets;
    }
}
