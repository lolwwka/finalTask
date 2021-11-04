package com.example.finalproject.service.bet;

import com.example.finalproject.dto.BetTeamDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface BetService {
    BetTeamDto addBet(String userEmail, long betValue, String teamName, long eventId);
}
