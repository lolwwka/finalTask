package com.example.finalproject.service;

import com.example.finalproject.entity.Team;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TeamService {
    boolean addTeam(String name);

    Team getTeam(String name);

    Team getTeam(int id);
}
