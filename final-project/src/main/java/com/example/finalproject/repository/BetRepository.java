package com.example.finalproject.repository;

import com.example.finalproject.entity.Bet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BetRepository extends JpaRepository<Bet, Long> {
    int countByEventAndVisitor(long event, long user);
}
