package com.example.finalproject.service.team;

import com.example.finalproject.entity.Team;
import com.example.finalproject.repository.TeamRepository;
import org.springframework.stereotype.Service;

@Service
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;

    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public boolean addTeam(String name) {
        teamRepository.save(new Team(name));
        return true;
    }

    @Override
    public Team getTeam(String name) {
        return teamRepository.getTeamByName(name);
    }

    @Override
    public Team getTeam(int id) {
        return teamRepository.getTeamById(id);
    }
}
