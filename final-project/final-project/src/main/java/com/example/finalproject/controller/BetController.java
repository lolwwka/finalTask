package com.example.finalproject.controller;

import com.example.finalproject.dto.BetTeamDto;
import com.example.finalproject.dto.GotBetDto;
import com.example.finalproject.service.bet.BetService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bet")
public class BetController {
    private final BetService betService;

    public BetController(BetService betService) {
        this.betService = betService;
    }

    @PostMapping
    public BetTeamDto addBet(@RequestBody GotBetDto betDto) {
        return betService.addBet(betDto.getUserMail(), betDto.getBetValue(), betDto.getTeamName(), betDto.getEventId());
    }
}
