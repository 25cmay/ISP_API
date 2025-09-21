package org.derryfield.isp2025.mlswebsite.service;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.derryfield.isp2025.mlswebsite.dao.MatchDao;
import org.derryfield.isp2025.mlswebsite.model.Match;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.derryfield.isp2025.mlswebsite.util.HibernateUtils;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class MatchService {

    private final MatchDao dao;

    @Transactional
    public Match createMatch(@NonNull @Validated final Match m) {
        log.info("DAO in service: " + dao);
        return dao.save(m);
    }

    @Transactional
    public Optional<Match> updateMatch(@NonNull final Long id, @NonNull @Validated final Match updatedMatchData) {
        return dao.findById(id).map(existing -> {
            existing.setMatchDate(updatedMatchData.getMatchDate());
            existing.setMatchTime(updatedMatchData.getMatchTime());
            existing.setHomeTeam(updatedMatchData.getHomeTeam());
            existing.setAwayTeam(updatedMatchData.getAwayTeam());
            existing.setHomeScore(updatedMatchData.getHomeScore());
            existing.setAwayScore(updatedMatchData.getAwayScore());
            existing.setCompetition(updatedMatchData.getCompetition());
            existing.setStadium(updatedMatchData.getStadium());
            existing.setRoundNumber(updatedMatchData.getRoundNumber());
            return dao.save(existing);
        });
    }

    @Transactional
    public boolean deleteMatch(@NonNull final Long id) {
        return dao.findById(id).map(match -> {
            dao.delete(match);
            return true;
        }).orElse(false);
    }

    @Transactional
    public Optional<Match> getMatchById(@NonNull final Long id) {
        Optional<Match> match = dao.findById(id);

        match.ifPresent(m -> {
          m.setHomeTeam(HibernateUtils.unproxy(m.getHomeTeam()));
          m.setAwayTeam(HibernateUtils.unproxy(m.getAwayTeam()));
          }
        );
    return match;
    }

    @Transactional
    public List<Match> getMatchesForTeam(@NonNull final String team) {
        return dao.getMatchesForTeam(team);
    }

    @Transactional
    public List<Match> getMatchesForCompetition(@NonNull final String competition) {
        return dao.getMatchHistoryForCompetiton(competition);
    }
}