package org.derryfield.isp2025.mlswebsite.dao;

import org.derryfield.isp2025.mlswebsite.model.Match;

import java.util.List;

public interface MatchDao extends EntityDao<Match, Long>{
    /**
     * Creates or updates a match in the dao
     */
    List<Match> getMatchesForTeam(String team);
    List<Match> getMatchupHistoryForTeams(String teamA, String teamB);
    List<Match> getMatchHistoryForCompetiton(String competiton);
}
