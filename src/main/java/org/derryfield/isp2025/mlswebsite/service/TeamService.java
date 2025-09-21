package org.derryfield.isp2025.mlswebsite.service;

import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.derryfield.isp2025.mlswebsite.dao.TeamDao;
import org.derryfield.isp2025.mlswebsite.model.Team;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamDao dao;

    @Transactional
    public Team createTeam(@NonNull @Validated final Team team) {
        log.info("Creating team: " + team.getName());
        return dao.save(team);
    }

    @Transactional
    public Optional<Team> updateTeam(@NonNull final Long id, @NonNull @Validated final Team updatedTeamData) {
        return dao.findById(id).map(existing -> {

            // Likeness
            existing.setName(updatedTeamData.getName());
            existing.setLocation(updatedTeamData.getLocation());

            // Team Stats
            existing.setExpectedGoals(updatedTeamData.getExpectedGoals());
            existing.setExpectedGoalsAgainst(updatedTeamData.getExpectedGoalsAgainst()); 
            existing.setAbbreviation(updatedTeamData.getAbbreviation());
            /*
            existing.setGoalsForPerMatch(updatedTeamData.getGoalsForPerMatch());
            existing.setPossessionPercentage(updatedTeamData.getPossessionPercentage());
            existing.setCleanSheets(updatedTeamData.getCleanSheets());
            existing.setShotsPerMatch(updatedTeamData.getShotsPerMatch());
            existing.setPenaltiesAwarded(updatedTeamData.getPenaltiesAwarded());
            existing.setGoalsAgainstPerMatch(updatedTeamData.getGoalsAgainstPerMatch());
            existing.setPenaltiesConceded(updatedTeamData.getPenaltiesConceded());
            existing.setSaves(updatedTeamData.getSaves());
            existing.setFoulsPerMatch(updatedTeamData.getFoulsPerMatch());
            existing.setYellowCards(updatedTeamData.getYellowCards());
            existing.setRedCards(updatedTeamData.getRedCards());
            */
            return dao.save(existing);
        });
    }

    @Transactional
    public boolean deleteTeam(@NonNull final Long id) {
        return dao.findById(id).map(team -> {
            dao.delete(team);
            return true;
        }).orElse(false);
    }

    public Optional<Team> getTeamById(@NonNull final Long id) {
        return dao.findById(id);
    }

    public List<Team> getAllTeams() {
        return dao.findAll();
    }

    public List<Team> getTeamsByBuild(@NonNull final String rosterBuild) {
        return dao.findByBuild(rosterBuild);
    }
}