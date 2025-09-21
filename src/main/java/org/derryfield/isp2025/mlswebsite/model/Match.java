package org.derryfield.isp2025.mlswebsite.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.derryfield.isp2025.mlswebsite.model.Team.TeamDTO;

/**
 * Base Data structure for a soccer match
 */
@Entity
@Table(name = "matches",
    indexes = {
        @Index(name = "idx_home_team", columnList = "homeTeam"),
        @Index(name = "idx_away_team", columnList = "awayTeam"),
        @Index(name = "idx_matchup_history", columnList = "homeTeam, awayTeam"),
        @Index(name = "idx_competition", columnList = "competition")
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Builder
public class Match extends LeafEntity<Long>{

    @Column(nullable = false)
    private LocalDate matchDate;

    @Column(nullable = false)
    private LocalTime matchTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "homeTeam", nullable = false, foreignKey = @ForeignKey(name = "fk_match_home_team"))
    private Team homeTeam;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "awayTeam", nullable = false, foreignKey = @ForeignKey(name = "fk_match_away_team"))
    private Team awayTeam;

    @Column(nullable = false)
    @Builder.Default
    private Integer homeScore = 0;

    @Column(nullable = false)
    @Builder.Default
    private Integer awayScore = 0;

    @Column
    private String competition;

    @Column
    private String stadium;

    @Column
    private Integer roundNumber;

    public record MatchDTO(
        Long id,
        LocalDate matchDate,
        LocalTime matchTime,
        TeamDTO homeTeam,
        TeamDTO awayTeam,
        Integer homeScore,
        Integer awayScore,
        String competition,
        String stadium,
        Integer roundNumber
    ) {
        public MatchDTO(Match match) {
            this(
                match.getId(),
                match.getMatchDate(),
                match.getMatchTime(),
                match.getHomeTeam() != null ? new TeamDTO(match.getHomeTeam()) : null,
                match.getAwayTeam() != null ? new TeamDTO(match.getAwayTeam()) : null,
                match.getHomeScore(),
                match.getAwayScore(),
                match.getCompetition(),
                match.getStadium(),
                match.getRoundNumber()
            );
        }
    }
}