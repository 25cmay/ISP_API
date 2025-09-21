package org.derryfield.isp2025.mlswebsite.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Builder
public class Team extends LeafEntity<Long> {

    // Likness
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String abbreviation;

    // Team statistics
    @Column
    private float expectedGoals;

    @Column
    private float expectedGoalsAgainst;

    public record TeamDTO(
        Long id,
        String name,
        String location,
        String abbreviation,
        float expectedGoals,
        float expectedGoalsAgainst
    ) {
        public TeamDTO(Team team) {
            this (
                team.getId(),
                team.getName(),
                team.getLocation(),
                team.getAbbreviation(),
                team.getExpectedGoals(),
                team.getExpectedGoalsAgainst()
            );
        }
    }
}

/*
@Column
private float goalsForPerMatch;

@Column
private float possessionPercentage;

@Column
private int cleanSheets;

@Column
private float goalsAgainstPerMatch;

@Column
private int yellowCards;

@Column
private int redCards;

@Column
private int penaltiesConceded;

@Column
private int saves;

@Column
private float foulsPerMatch;

@Column
private float shotsPerMatch;

@Column
private int penaltiesAwarded;

*/