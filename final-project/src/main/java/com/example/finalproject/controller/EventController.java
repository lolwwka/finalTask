package com.example.finalproject.controller;

import com.example.finalproject.dto.Result;
import com.example.finalproject.dto.event.EventDto;
import com.example.finalproject.dto.event.EventDtoWithBets;
import com.example.finalproject.dto.event.EventWithTeams;
import com.example.finalproject.service.event.EventService;
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
    public Result addEvent(@Valid @RequestBody EventDto eventDto) {
        String tournamentName = eventDto.getTournamentName();
        String firstTeamName = eventDto.getFirstTeamName();
        String secondTeamName = eventDto.getSecondTeamName();
        return new Result(eventService.addEvent(firstTeamName, secondTeamName, tournamentName));
    }

    @GetMapping
    public List<EventWithTeams> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping(value = "/{id}")
    public EventDtoWithBets getEvent(@PathVariable(value = "id") long eventId) {
        System.out.println();
        return eventService.getEvent(eventId);
    }
}
