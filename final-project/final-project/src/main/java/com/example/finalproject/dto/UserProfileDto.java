package com.example.finalproject.dto;

import com.example.finalproject.entity.Bet;

import java.util.List;

public class UserProfileDto {
    private long balance;
    private List<BetEventDto> betEventDto;

    public UserProfileDto() {
    }

    public UserProfileDto(long balance, List<BetEventDto> betEventDto) {
        this.balance = balance;
        this.betEventDto = betEventDto;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }
}
