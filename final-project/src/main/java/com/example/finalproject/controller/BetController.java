package com.example.finalproject.controller;

import com.example.finalproject.dto.BetTeamDto;
import com.example.finalproject.dto.GotBetDto;
import com.example.finalproject.service.bet.BetService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/bet")
public class BetController {
    private final BetService betService;

    public BetController(BetService betService) {
        this.betService = betService;
    }

    @PostMapping
    public BetTeamDto addBet(@RequestBody GotBetDto betDto, Principal principal) {
        return betService.addBet(principal.getName(), betDto.getBetValue(), betDto.getTeamName(), betDto.getEventId());
    }
}
