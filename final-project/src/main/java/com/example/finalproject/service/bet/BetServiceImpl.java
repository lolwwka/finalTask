package com.example.finalproject.service.bet;

import com.example.finalproject.dto.BetTeamDto;
import com.example.finalproject.entity.Bet;
import com.example.finalproject.entity.Event;
import com.example.finalproject.entity.User;
import com.example.finalproject.repository.BetRepository;
import com.example.finalproject.repository.EventRepository;
import com.example.finalproject.repository.VisitorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class BetServiceImpl implements BetService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BetServiceImpl.class);
    private final BetRepository BET_REPOSITORY;
    private final EventRepository EVENT_REPOSITORY;
    private final VisitorRepository VISITOR_REPOSITORY;

    public BetServiceImpl(BetRepository BET_REPOSITORY, EventRepository EVENT_REPOSITORY, VisitorRepository VISITOR_REPOSITORY) {
        this.BET_REPOSITORY = BET_REPOSITORY;
        this.EVENT_REPOSITORY = EVENT_REPOSITORY;
        this.VISITOR_REPOSITORY = VISITOR_REPOSITORY;
    }

    @Override
    public BetTeamDto addBet(String userEmail, long betValue, String teamName, long eventId) {
        User user = VISITOR_REPOSITORY.findByEmail(userEmail);
        if (user.getBalance() < betValue) {
            LOGGER.error("User {} have no money for bet", userEmail);
            throw new RuntimeException("No such money on account");
        }
        user.setBalance(user.getBalance() - betValue);
        long betsOnThisEvent = BET_REPOSITORY.countByUserBetNum(eventId, VISITOR_REPOSITORY.findByEmail(userEmail).getId());
        if (betsOnThisEvent > 2) {
            LOGGER.error("User {} already made 3 bets", userEmail);
            return new BetTeamDto(true);
        }
        Event event = EVENT_REPOSITORY.getById(eventId);
        if (event.getTeams().get(0).getName().equals(teamName))
            event.setFirstTeamAmount(event.getFirstTeamAmount() + betValue);
        else event.setSecondTeamAmount(event.getSecondTeamAmount() + betValue);
        Bet bet = new Bet(betValue);
        bet.setUser(user);
        bet.setEvent(EVENT_REPOSITORY.getById(eventId));
        bet.setStatus("In process");
        bet.setTeamName(teamName);
        BET_REPOSITORY.save(bet);
        BetTeamDto betTeamDto = new BetTeamDto();
        betTeamDto.setFirstTeamAmount(event.getFirstTeamAmount());
        betTeamDto.setSecondTeamAmount(event.getSecondTeamAmount());
        betTeamDto.setException(false);
        return betTeamDto;
    }
}
