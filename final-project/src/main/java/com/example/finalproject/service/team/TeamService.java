package com.example.finalproject.service.team;

import com.example.finalproject.dto.TeamDto;
import com.example.finalproject.entity.Team;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface TeamService {
    boolean addTeam(String name);

    Team getTeam(String name);

    Team getTeam(int id);

    List<TeamDto> getTeams();
}
