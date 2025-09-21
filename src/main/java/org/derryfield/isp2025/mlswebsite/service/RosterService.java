package org.derryfield.isp2025.mlswebsite.service;

import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.derryfield.isp2025.mlswebsite.dao.RosterDao;
import org.derryfield.isp2025.mlswebsite.model.Roster;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class RosterService {

    private final RosterDao dao;

    @Transactional
    public Roster createRoster(@NonNull @Validated final Roster r) {
        log.info("Creating new roster entry: " + r);
        return dao.save(r);
    }

    @Transactional
    public Optional<Roster> updateRoster(@NonNull final Long id, @NonNull @Validated final Roster updatedRosterData) {
        return dao.findById(id).map(existing -> {
            existing.setPlayer(updatedRosterData.getPlayer());
            existing.setTeam(updatedRosterData.getTeam());
            existing.setSeason(updatedRosterData.getSeason());
            existing.setSubseason(updatedRosterData.getSubseason());
            existing.setRosterNumber(updatedRosterData.getRosterNumber());
            existing.setStatus(updatedRosterData.getStatus());
            existing.setRosterDesignation(updatedRosterData.getRosterDesignation());
            existing.setRosterBuild(updatedRosterData.getRosterBuild());
            existing.setGeneralAllocationMoney(updatedRosterData.getGeneralAllocationMoney());
            existing.setRosterSize(updatedRosterData.getRosterSize());
            existing.setInternationalSlots(updatedRosterData.getInternationalSlots());

            return dao.save(existing);
        });
    }

    @Transactional
    public boolean deleteRoster(@NonNull final Long id) {
        return dao.findById(id).map(roster -> {
            dao.delete(roster);
            return true;
        }).orElse(false);
    }

    public Optional<Roster> getRosterById(@NonNull final Long id) {
        return dao.findById(id);
    }

    public List<Roster> getRostersBySeason(@NonNull final String season) {
        return dao.findBySeason(season);
    }

    public List<Roster> getRostersByTeamName(@NonNull final String team) {
        return dao.findByTeam(team);
    }
}