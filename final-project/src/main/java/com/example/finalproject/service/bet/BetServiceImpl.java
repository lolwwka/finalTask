package com.example.finalproject.service.bet;

import com.example.finalproject.dto.BetTeamDto;
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
    public BetTeamDto addBet(String userEmail, long betValue, String teamName, long eventId) {
        User user = userRepository.findByEmail(userEmail);
        user.setBalance(user.getBalance()- betValue);
        if(user.getBalance() < betValue){
            throw new RuntimeException("No such money on account");
        }
        long betsOnThisEvent = betRepository.countByUserBetNum(eventId ,userRepository.findByEmail(userEmail).getId());
        if (betsOnThisEvent > 2) {
            return new BetTeamDto(true);
        }  // ?????? Как сделать перебор по ставке на событии
        Event event = eventRepository.getById(eventId);
        if (event.getTeams().get(0).getName().equals(teamName))
            event.setFirstTeamAmount(event.getFirstTeamAmount() + betValue);
        else event.setSecondTeamAmount(event.getSecondTeamAmount() + betValue);
        Bet bet = new Bet(betValue);
        bet.setUser(user);
        bet.setEvent(eventRepository.getById(eventId));
        bet.setStatus("In process");
        betRepository.save(bet);
        BetTeamDto betTeamDto = new BetTeamDto();
        betTeamDto.setFirstTeamAmount(event.getFirstTeamAmount());
        betTeamDto.setSecondTeamAmount(event.getSecondTeamAmount());
        betTeamDto.setException(false);
        return betTeamDto;
    }
}
