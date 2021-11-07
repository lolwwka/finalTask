package com.example.finalproject.service.team;

import com.example.finalproject.dto.TeamDto;
import com.example.finalproject.entity.Team;
import com.example.finalproject.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;

    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public boolean addTeam(String name) {
        if(teamRepository.findByName(name) != null){
            throw new RuntimeException("Team is already created");
        }
        teamRepository.save(new Team(name));
        return true;
    }

    @Override
    public List<TeamDto> getTeams() {
        List<Team> teams = teamRepository.findAll();
        List<TeamDto> result = new ArrayList<>();
        for(Team team : teams){
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
