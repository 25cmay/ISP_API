package org.derryfield.isp2025.mlswebsite.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

import org.derryfield.isp2025.mlswebsite.model.enums.AcquisitionMethod;
import org.derryfield.isp2025.mlswebsite.model.enums.RosterStatus;
import org.derryfield.isp2025.mlswebsite.model.enums.SpecialContractDesignations;

/**
 * Base data structure for a player contract
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Builder

public class Contract extends LeafEntity<Long> {

    // foreign keys identifying 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = false, foreignKey = @ForeignKey(name = "fk_contract_team"))
    private Team team;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id", nullable = false, foreignKey = @ForeignKey(name = "fk_contract_player"))
    private Player player;

    // transfer details
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AcquisitionMethod acquisitionMethod;

    @Column
    private String teamAquiredFrom;

    // contract dates
    @Column(nullable = false)
    private LocalDate dateSigned;

    @Column(nullable = false)
    private LocalDate contractEnd;

    @Column(nullable = false)
    private int contractOptions;

    // Money
    // non adjusted not always cap hit
    @Column(nullable = false)
    private Double wage;

    @Column(nullable = false)
    private Double capHit;

    @Column(nullable = false)
    private Double generalAllocationMoneyUsed;
}