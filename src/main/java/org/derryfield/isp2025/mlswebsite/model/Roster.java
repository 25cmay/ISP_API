package org.derryfield.isp2025.mlswebsite.model;

import jakarta.persistence.*;

import org.derryfield.isp2025.mlswebsite.model.enums.RosterBuild;
import org.derryfield.isp2025.mlswebsite.model.enums.RosterStatus;
import org.derryfield.isp2025.mlswebsite.model.enums.SpecialContractDesignations;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


// The basic structure for a roster
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Builder
public class Roster extends LeafEntity<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = false, foreignKey = @ForeignKey(name = "fk_roster_team"))
    private Team team;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id", nullable = false, foreignKey = @ForeignKey(name = "fk_roster_player"))
    private Player player;

    @Column(nullable = false)
    private String season; 

    // Allows us to deal with contractual clauses that go by six month intervals
    @Column(nullable = false)
    private int subseason; // 1 is january first 2 is july first

    @Column(nullable = false)
    private int rosterNumber; // 1-20 senior, 20-31 supplemental, 31
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RosterStatus status; // "Unavailable – On Loan", "Loan Player", "Unavailable - Injured List", "Unavailable – SEI"

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SpecialContractDesignations rosterDesignation; // "Young Dp", "Dp", "U22 initiative" "HG Player" "Professional Player Development Role" "Generation adidas"

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RosterBuild rosterBuild; // dp or u-22 

    @Column
    private int generalAllocationMoney;

    @Column(nullable = false)
    private int rosterSize;

    @Column
    private int internationalSlots;


}