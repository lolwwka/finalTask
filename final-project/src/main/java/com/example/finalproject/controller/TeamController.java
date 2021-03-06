package com.example.finalproject.controller;

import com.example.finalproject.dto.Result;
import com.example.finalproject.dto.TeamDto;
import com.example.finalproject.service.team.TeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/team")
public class TeamController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TeamController.class);
    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping
    public Result addTeam(@Valid @RequestBody TeamDto teamDto, Principal principal) {
        LOGGER.info("User {} adding new team with name {}", principal.getName(), teamDto.getName());
        teamService.addTeam(teamDto.getName());
        return new Result(true);
    }

    @GetMapping
    public List<TeamDto> getAllTeams() {
        return teamService.getTeams();
    }
}
