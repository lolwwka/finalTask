package com.example.finalproject.service.bet;

import com.example.finalproject.dto.BetTeamDto;
import com.example.finalproject.entity.Bet;
import com.example.finalproject.entity.Event;
import com.example.finalproject.entity.User;
import com.example.finalproject.repository.BetRepository;
import com.example.finalproject.repository.EventRepository;
import com.example.finalproject.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class BetServiceImpl implements BetService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BetServiceImpl.class);
    private final BetRepository betRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public BetServiceImpl(BetRepository betRepository, EventRepository eventRepository, UserRepository userRepository) {
        this.betRepository = betRepository;
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    @Override
    public BetTeamDto addBet(String userEmail, long betValue, String teamName, long eventId) {
        User user = userRepository.findByEmail(userEmail);
        if (user.getBalance() < betValue) {
            LOGGER.error("User {} have no money for bet", userEmail);
            throw new RuntimeException("No such money on account");
        }
        user.setBalance(user.getBalance() - betValue);
        long betsOnThisEvent = betRepository.countByUserBetNum(eventId, userRepository.findByEmail(userEmail).getId());
        if (betsOnThisEvent > 2) {
            LOGGER.error("User {} already made 3 bets", userEmail);
            return new BetTeamDto(true);
        }
        Event event = eventRepository.getById(eventId);
        if (event.getTeams().get(0).getName().equals(teamName))
            event.setFirstTeamAmount(event.getFirstTeamAmount() + betValue);
        else event.setSecondTeamAmount(event.getSecondTeamAmount() + betValue);
        Bet bet = new Bet(betValue);
        bet.setUser(user);
        bet.setEvent(eventRepository.getById(eventId));
        bet.setStatus("In process");
        bet.setTeamName(teamName);
        betRepository.save(bet);
        BetTeamDto betTeamDto = new BetTeamDto();
        betTeamDto.setFirstTeamAmount(event.getFirstTeamAmount());
        betTeamDto.setSecondTeamAmount(event.getSecondTeamAmount());
        betTeamDto.setException(false);
        return betTeamDto;
    }
}
