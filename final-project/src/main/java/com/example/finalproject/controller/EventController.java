package com.example.finalproject.controller;

import com.example.finalproject.dto.Result;
import com.example.finalproject.dto.event.EventDto;
import com.example.finalproject.dto.event.EventDtoWithBets;
import com.example.finalproject.dto.event.EventWithTeams;
import com.example.finalproject.service.event.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {
    private final EventService eventService;
    private static final Logger LOGGER = LoggerFactory.getLogger(EventController.class);

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public Result addEvent(@Valid @RequestBody EventDto eventDto, Principal principal) {
        LOGGER.info("User {} adding new event, tournament : {}. first team : {}, second team {}", principal.getName(),
                eventDto.getTournamentName(), eventDto.getFirstTeamName(), eventDto.getSecondTeamName());
        String tournamentName = eventDto.getTournamentName();
        String firstTeamName = eventDto.getFirstTeamName();
        String secondTeamName = eventDto.getSecondTeamName();
        return new Result(eventService.addEvent(firstTeamName, secondTeamName, tournamentName));
    }

    @GetMapping
    public List<EventWithTeams> getAllEvents(Principal principal) {
        return eventService.getAllEvents(principal.getName());
    }

    @PutMapping(value = "/{id}")
    public Result changeEventStatus(@PathVariable(value = "id") long id,
                                    @RequestBody EventWithTeams eventDto, Principal principal) {
        LOGGER.info("User {} changing event {} with status {}", principal.getName(), id, eventDto.getStatus());
        return new Result(eventService.changeEventStatus(id, eventDto.getStatus(), eventDto.getWinningTeam()));
    }

    @GetMapping(value = "/{id}")
    public EventDtoWithBets getEvent(@PathVariable(value = "id") long eventId) {
        System.out.println();
        return eventService.getEvent(eventId);
    }
}
