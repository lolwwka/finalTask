package com.example.finalproject.controller;

import com.example.finalproject.dto.EventDto;
import com.example.finalproject.dto.EventWithTeams;
import com.example.finalproject.entity.Event;
import com.example.finalproject.service.EventService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/event")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public Map<String, String> addEvent(@Valid @RequestBody EventDto eventDto) {
        String tournamentName = eventDto.getTournamentName();
        String firstTeamName = eventDto.getFirstTeamName();
        String secondTeamName = eventDto.getSecondTeamName();
        eventService.addEvent(firstTeamName, secondTeamName, tournamentName);
        Map<String, String> result = new HashMap<>();
        result.put("confirmed", "true");
        return result;
    }

    @GetMapping
    public List<EventWithTeams> getAllEvents() {
        return eventService.getAllEvents();
    }
}
