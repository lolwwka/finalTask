package com.example.finalproject.controller;

import com.example.finalproject.dto.BetTeamDto;
import com.example.finalproject.dto.GotBetDto;
import com.example.finalproject.service.bet.BetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/bet")
public class BetController {
    private final BetService betService;
    private static final Logger LOGGER = LoggerFactory.getLogger(BetController.class);

    public BetController(BetService betService) {
        this.betService = betService;
    }

    @PostMapping
    public BetTeamDto addBet(@RequestBody GotBetDto betDto, Principal principal) {
        LOGGER.info("User {} making a bet on {} event with {} amount", principal.getName(), betDto.getEventId(), betDto.getBetValue());
        return betService.addBet(principal.getName(), betDto.getBetValue(), betDto.getTeamName(), betDto.getEventId());
    }
}
