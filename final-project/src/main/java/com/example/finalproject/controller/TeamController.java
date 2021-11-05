package com.example.finalproject.controller;

import com.example.finalproject.dto.TeamDto;
import com.example.finalproject.service.team.TeamService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/team")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping
    public Map<String, String> addTeam(@Valid @RequestBody TeamDto teamDto) {
        teamService.addTeam(teamDto.getName());
        HashMap<String, String> result = new HashMap<>();
        result.put("confirmed", "true");
        return result;
    }
    @GetMapping
    public List<TeamDto> getAllTeams(){
        return teamService.getTeams();
    }
}
