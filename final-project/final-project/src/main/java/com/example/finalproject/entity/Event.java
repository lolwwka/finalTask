package com.example.finalproject.entity;

import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Validated
@Entity
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    private long firstTeamAmount;
    private long secondTeamAmount;
    private String tournamentName;

    @OneToMany(mappedBy = "event")
    private List<Bet> bets;

    @ManyToMany(fetch = FetchType.EAGER)
    @Size(min = 2, max = 2)
    @JoinTable(name = "team_event",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id"))
    private List<Team> teams;

    public List<Bet> getBets() {
        return bets;
    }

    public void setBets(List<Bet> bets) {
        this.bets = bets;
    }

    public long getFirstTeamAmount() {
        return firstTeamAmount;
    }

    public void setFirstTeamAmount(long firstTeamAmount) {
        this.firstTeamAmount = firstTeamAmount;
    }

    public long getSecondTeamAmount() {
        return secondTeamAmount;
    }

    public void setSecondTeamAmount(long secondTeamAmount) {
        this.secondTeamAmount = secondTeamAmount;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public long getId() {
        return id;
    }

    public String getTournamentName() {
        return tournamentName;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }
}
