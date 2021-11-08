package com.example.finalproject.service.team;

import com.example.finalproject.dto.TeamDto;
import com.example.finalproject.entity.Team;
import com.example.finalproject.repository.TeamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(TeamServiceImpl.class);

    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public boolean addTeam(String name) {
        if (teamRepository.findByName(name) != null) {
            LOGGER.error("Someone trying to create the same team");
            throw new RuntimeException("Team is already created");
        }
        teamRepository.save(new Team(name));
        LOGGER.info("Someone created new team [{}]", name);
        return true;
    }

    @Override
    public List<TeamDto> getTeams() {
        List<Team> teams = teamRepository.findAll();
        List<TeamDto> result = new ArrayList<>();
        for (Team team : teams) {
            result.add(new TeamDto(team.getName()));
        }
        return result;
    }

    @Override
    public Team getTeam(String name) {
        return teamRepository.findByName(name);
    }

    @Override
    public Team getTeam(int id) {
        return teamRepository.getTeamById(id);
    }
}
