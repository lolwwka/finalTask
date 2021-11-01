package com.example.finalproject.service;

import com.example.finalproject.dto.EventWithTeams;
import com.example.finalproject.entity.Event;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface EventService {
    boolean addEvent(String firstTeam, String secondTeam, String tournamentName);

    List<EventWithTeams> getAllEvents();
}
