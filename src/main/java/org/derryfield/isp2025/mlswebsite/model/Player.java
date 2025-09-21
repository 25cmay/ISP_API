package org.derryfield.isp2025.mlswebsite.model;

import java.time.LocalDate;

import org.derryfield.isp2025.mlswebsite.model.enums.Position;
import org.derryfield.isp2025.mlswebsite.model.enums.PreferredFoot;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


// The basic structure for a player


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Builder

public class Player extends LeafEntity<Long>{

    // Pro Career Stats
  
    @Column(nullable = false)
    @Builder.Default
    private int careerGoals = 0;

    @Column(nullable = false)
    @Builder.Default
    private int careerAssists = 0;

    @Column(nullable = false)
    @Builder.Default
    private int careerRedCards = 0;
    
    @Column(nullable = false)
    @Builder.Default
    private int careerYellowCards = 0;

    @Column(nullable = false)
    @Builder.Default
    private int careerGamesPlayed = 0;

    @Column(nullable = false)
    @Builder.Default
    private int careerCleanSheets = 0;

    // This seasons stats

    @Column(nullable = false)
    @Builder.Default
    private int seasonGoals = 0;

    @Column(nullable = false)
    @Builder.Default
    private int seasonPenaltiesScored = 0;

    @Column(nullable = false)
    @Builder.Default
    private int seasonAssists = 0;

    @Column(nullable = false)
    @Builder.Default
    private int seasonRedCards = 0;

    @Column(nullable = false)
    @Builder.Default
    private int seasonYellowCards = 0;

    @Column(nullable = false)
    @Builder.Default
    private int seasonGamesPlayed = 0;

    @Column(nullable = false)
    @Builder.Default
    private float seasonAverageRating = 0;

    @Column(nullable = false)
    @Builder.Default
    private int seasonCleanSheets = 0;

    // Player likness

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Position position;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false)
    private String country;

    // inches 
    @Column(nullable = false)
    private int height;

    // pounds
    @Column(nullable = false)
    private int weight;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PreferredFoot preferredFoot;

    @Column
    private int jerseyNumber;

    // A players transfer value in the market 
    @Column 
    private float marketValue;

    @Column
    private String currentTeam;

    // more if wanted: past seasons, teams played for, recent matches, seasons

}
