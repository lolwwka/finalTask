package com.example.finalproject.service.event;

import com.example.finalproject.dto.event.EventDtoWithBets;
import com.example.finalproject.dto.event.EventWithTeams;
import com.example.finalproject.entity.Bet;
import com.example.finalproject.entity.Event;
import com.example.finalproject.entity.Role;
import com.example.finalproject.entity.Team;
import com.example.finalproject.repository.EventRepository;
import com.example.finalproject.repository.UserRepository;
import com.example.finalproject.service.team.TeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventServiceImpl.class);
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final TeamService teamService;

    public EventServiceImpl(UserRepository userRepository, EventRepository eventRepository, TeamService teamService) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.teamService = teamService;
    }

    @Override
    public List<EventWithTeams> getAllEvents(String login) {
        ArrayList<Event> events = new ArrayList<>(eventRepository.findAll(Sort.by(Sort.Direction.DESC, "status")));
        ArrayList<Role> roles = new ArrayList<>(userRepository.findByEmail(login).getRoles());
        boolean isAdmin = false;
        for (Role role : roles) {
            if (role.getName().equals("ADMIN")) {
                isAdmin = true;
                break;
            }
        }
        ArrayList<EventWithTeams> eventWithTeams = new ArrayList<>();
        for (Event event : events) {
            if (!event.getStatus().equals("Preparation") && !isAdmin && !event.getStatus().equals("Playing")) {
                continue;
            }
            EventWithTeams eventDto = new EventWithTeams();
            eventDto.setFirstTeamName(event.getTeams().get(0).getName());
            eventDto.setSecondTeamName(event.getTeams().get(1).getName());
            eventDto.setTournamentName(event.getTournamentName());
            eventDto.setId(event.getId());
            eventDto.setStatus(event.getStatus());
            eventWithTeams.add(eventDto);
        }
        return eventWithTeams;
    }

    @Override
    public boolean addEvent(String firstTeamName, String secondTeamName, String tournamentName) {
        if (firstTeamName.equals(secondTeamName)) {
            LOGGER.error("Someone tries to create event with 2 same teams");
            throw new RuntimeException("Can't create event with 2 same teams");
        }
        Event event = new Event();
        ArrayList<Team> teams = new ArrayList<>();
        teams.add(teamService.getTeam(firstTeamName));
        teams.add(teamService.getTeam(secondTeamName));
        event.setTeams(teams);
        event.setTournamentName(tournamentName);
        event.setStatus("Preparation");
        eventRepository.save(event);
        return true;
    }

    @Override
    public EventDtoWithBets getEvent(long id) {
        Event event = eventRepository.getById(id);
        EventDtoWithBets eventDto = new EventDtoWithBets();
        eventDto.setFirstTeam(event.getTeams().get(0).getName());
        eventDto.setSecondTeam(event.getTeams().get(1).getName());
        eventDto.setFirstTeamAmount(event.getFirstTeamAmount());
        eventDto.setSecondTeamAmount(event.getSecondTeamAmount());
        eventDto.setTournament(event.getTournamentName());
        eventDto.setStatus(event.getStatus());
        return eventDto;
    }

    @Override
    public boolean changeEventStatus(long eventId, String newStatus, String winningTeam) {
        Event event = eventRepository.getById(eventId);
        if (newStatus.equals("Preparation")) {
            event.setStatus("Preparation");
        }
        if (newStatus.equals("Playing")) {
            event.setStatus("Playing");
            ArrayList<Bet> bets = new ArrayList<>(event.getBets());
            for (Bet bet : bets) {
                bet.setStatus("Match playing");
            }
        }
        if (newStatus.equals("Finish")) {
            ArrayList<Bet> bets = new ArrayList<>(event.getBets());
            event.setStatus("Finished won(" + winningTeam + ")");
            calculateUserMoney(event, bets, winningTeam);
        }
        return true;
    }

    private void calculateUserMoney(Event event, List<Bet> bets, String winningTeam) {
        double ourProfit = 0.05;
        double ourWinAmount = (event.getFirstTeamAmount() + event.getSecondTeamAmount()) * ourProfit;
        double usersAmountWithoutProfit = event.getFirstTeamAmount() + event.getSecondTeamAmount() - ourWinAmount;
        double firstTeamIndex = usersAmountWithoutProfit / event.getFirstTeamAmount();
        double secondTeamIndex = usersAmountWithoutProfit / event.getSecondTeamAmount();
        for (Bet bet : bets) {
            if (event.getTeams().get(0).getName().equals(winningTeam) && bet.getTeamName().equals(winningTeam)) {
                bet.getUser().setBalance((long) (bet.getUser().getBalance() + bet.getAmount() * firstTeamIndex));
                bet.setStatus("Won");
                continue;
            } else {
                if (bet.getTeamName().equals(winningTeam)) {
                    bet.getUser().setBalance((long) (bet.getUser().getBalance() + bet.getAmount() * secondTeamIndex));
                    bet.setStatus("Won");
                    continue;
                }
            }
            bet.setStatus("Lost");
        }
    }
}
