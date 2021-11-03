package com.example.finalproject.service.event;

import com.example.finalproject.dto.event.EventDtoWithBets;
import com.example.finalproject.dto.event.EventWithTeams;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface EventService {
    boolean addEvent(String firstTeam, String secondTeam, String tournamentName);

    List<EventWithTeams> getAllEvents();

    EventDtoWithBets getEvent(long id);
}
