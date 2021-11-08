package com.example.finalproject.repository;

import com.example.finalproject.entity.Bet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BetRepository extends JpaRepository<Bet, Long> {
    @Query(value = "SELECT count (b.status) from Bet b where " +
            "b.event.id = :eventId and b.user.id = :userId")
    int countByUserBetNum(@Param("eventId") long eventId, @Param("userId") long userId);
}
