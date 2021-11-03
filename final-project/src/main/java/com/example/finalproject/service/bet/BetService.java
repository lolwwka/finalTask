package com.example.finalproject.service.bet;

import com.example.finalproject.dto.BetDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface BetService {
    BetDto addBet(String userEmail, long betValue, String teamName, long eventId);
}
