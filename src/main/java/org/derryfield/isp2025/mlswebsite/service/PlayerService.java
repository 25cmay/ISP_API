package org.derryfield.isp2025.mlswebsite.service;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.derryfield.isp2025.mlswebsite.dao.PlayerDao;
import org.derryfield.isp2025.mlswebsite.model.Player;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerDao dao;

    @Transactional
    public Player createPlayer(@NonNull @Validated final Player p) {
        log.info("DAO in service: " + dao);
        return dao.save(p);
    }

    @Transactional
    public Optional<Player> updatePlayer(@NonNull final Long id, @NonNull @Validated final Player updatedPlayerData) {
        return dao.findById(id).map(existing -> {

            // Pro Career Stats
            existing.setCareerGoals(updatedPlayerData.getCareerGoals());
            existing.setCareerAssists(updatedPlayerData.getCareerAssists());
            existing.setCareerRedCards(updatedPlayerData.getCareerRedCards());
            existing.setCareerYellowCards(updatedPlayerData.getCareerYellowCards());
            existing.setCareerGamesPlayed(updatedPlayerData.getCareerGamesPlayed());
            existing.setCareerCleanSheets(updatedPlayerData.getCareerCleanSheets());

            // This seasons stats
            existing.setSeasonGoals(updatedPlayerData.getSeasonGoals());
            existing.setSeasonPenaltiesScored(updatedPlayerData.getSeasonPenaltiesScored());
            existing.setSeasonAssists(updatedPlayerData.getSeasonAssists());
            existing.setSeasonRedCards(updatedPlayerData.getSeasonRedCards());
            existing.setSeasonYellowCards(updatedPlayerData.getSeasonYellowCards());
            existing.setSeasonGamesPlayed(updatedPlayerData.getSeasonGamesPlayed());
            existing.setSeasonAverageRating(updatedPlayerData.getSeasonAverageRating());
            existing.setSeasonCleanSheets(updatedPlayerData.getSeasonCleanSheets());

            // Player likness
            existing.setPosition(updatedPlayerData.getPosition());
            existing.setName(updatedPlayerData.getName());
            existing.setCountry(updatedPlayerData.getCountry());
            existing.setHeight(updatedPlayerData.getHeight());
            existing.setWeight(updatedPlayerData.getWeight());
            existing.setPreferredFoot(updatedPlayerData.getPreferredFoot());
            existing.setJerseyNumber(updatedPlayerData.getJerseyNumber());
            existing.setMarketValue(updatedPlayerData.getMarketValue());
            existing.setCurrentTeam(updatedPlayerData.getCurrentTeam());

            return dao.save(existing);
        });
    }

    @Transactional
    public boolean deletePlayer(@NonNull final Long id) {
        return dao.findById(id).map(player -> {
            dao.delete(player);
            return true;
        }).orElse(false);
    }

    public Optional<Player> getPlayerById(@NonNull final Long id) {
        return dao.findById(id);
    }

    public List<Player> getPlayersByTeam(@NonNull final String teamName) {
        return dao.findByCurrentTeam(teamName);
    }

    public List<Player> getPlayersByCountry(@NonNull final String country) {
        return dao.findByCountry(country);
    }
}
