package com.example.finalproject.service.bet;

import com.example.finalproject.dto.BetDto;
import com.example.finalproject.entity.Bet;
import com.example.finalproject.entity.Event;
import com.example.finalproject.entity.User;
import com.example.finalproject.repository.BetRepository;
import com.example.finalproject.repository.EventRepository;
import com.example.finalproject.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class BetServiceImpl implements BetService {
    private final BetRepository betRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public BetServiceImpl(BetRepository betRepository, EventRepository eventRepository, UserRepository userRepository) {
        this.betRepository = betRepository;
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    @Override
    public BetDto addBet(String userEmail, long betValue, String teamName, long eventId) {
        if (betRepository.countByUserId(userRepository.findByEmail(userEmail).getId()) > 3) {
            return new BetDto(true);
        }
        Event event = eventRepository.getById(eventId);
        if (event.getTeams().get(0).getName().equals(teamName))
            event.setFirstTeamAmount(event.getFirstTeamAmount() + betValue);
        else event.setSecondTeamAmount(event.getSecondTeamAmount() + betValue);
        User user = userRepository.findByEmail(userEmail);
        Bet bet = new Bet(betValue);
        bet.setUser(user);
        betRepository.save(bet);
        BetDto betDto = new BetDto();
        betDto.setFirstTeamAmount(event.getFirstTeamAmount());
        betDto.setSecondTeamAmount(event.getSecondTeamAmount());
        betDto.setException(false);
        return betDto;
    }
}
