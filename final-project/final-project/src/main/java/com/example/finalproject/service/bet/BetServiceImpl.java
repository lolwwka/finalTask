package com.example.finalproject.service.bet;

import com.example.finalproject.dto.BetTeamDto;
import com.example.finalproject.entity.Bet;
import com.example.finalproject.entity.Event;
import com.example.finalproject.entity.User;
import com.example.finalproject.repository.BetRepository;
import com.example.finalproject.repository.EventRepository;
import com.example.finalproject.repository.VisitorRepository;
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
    public BetTeamDto addBet(String userEmail, long betValue, String teamName, long eventId) {
        long betsOnThisEvent = betRepository.countByUserIdAndEventId(userRepository.findByEmail(userEmail).getId(), eventId);
        if (betsOnThisEvent > 3) {
            return new BetTeamDto(true);

        }  // ?????? Как сделать перебор по ставке на событии
        Event event = eventRepository.getById(eventId);
        if (event.getTeams().get(0).getName().equals(teamName))
            event.setFirstTeamAmount(event.getFirstTeamAmount() + betValue);
        else event.setSecondTeamAmount(event.getSecondTeamAmount() + betValue);
        User user = userRepository.findByEmail(userEmail);
        Bet bet = new Bet(betValue);
        bet.setUser(user);
        bet.setEvent(eventRepository.getById(eventId));
        betRepository.save(bet);
        BetTeamDto betTeamDto = new BetTeamDto();
        betTeamDto.setFirstTeamAmount(event.getFirstTeamAmount());
        betTeamDto.setSecondTeamAmount(event.getSecondTeamAmount());
        betTeamDto.setException(false);
        return betTeamDto;
    }
}
