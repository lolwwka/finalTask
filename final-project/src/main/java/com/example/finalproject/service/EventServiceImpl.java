package com.example.finalproject.service;

import com.example.finalproject.dto.EventWithTeams;
import com.example.finalproject.entity.Event;
import com.example.finalproject.entity.Team;
import com.example.finalproject.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final TeamService teamService;

    public EventServiceImpl(EventRepository eventRepository, TeamService teamService) {
        this.eventRepository = eventRepository;
        this.teamService = teamService;
    }

    @Override
    public List<EventWithTeams> getAllEvents() {
        ArrayList<Event> events = new ArrayList<>(eventRepository.findAll());
        ArrayList<EventWithTeams> eventWithTeams = new ArrayList<>();
        for (Event event: events) {
            EventWithTeams eventDto = new EventWithTeams();
            eventDto.setFirstTeamName(event.getTeams().get(0).getName());
            eventDto.setSecondTeamName(event.getTeams().get(1).getName());
            eventDto.setFirstTeamAmount(event.getFirstTeamAmount());
            eventDto.setSecondTeamAmount(event.getSecondTeamAmount());
            eventDto.setTournamentName(event.getTournamentName());
            eventWithTeams.add(eventDto);
        }
        return eventWithTeams;
    }

    @Override
    public boolean addEvent(String firstTeamName, String secondTeamName, String tournamentName) {
        Event event = new Event();
//        HashSet<Team> teams = new HashSet<>();
        ArrayList<Team> teams = new ArrayList<>();
        teams.add(teamService.getTeam(firstTeamName));
        teams.add(teamService.getTeam(secondTeamName));
        event.setTeams(teams);
        event.setTournamentName(tournamentName);
        eventRepository.save(event);
        return true;
    }
}
