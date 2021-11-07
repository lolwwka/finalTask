package com.example.finalproject.repository;

import com.example.finalproject.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
    Team findByName(String name);

    Team getTeamById(int id);
}
