package org.derryfield.isp2025.mlswebsite.dao.mysql;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.derryfield.isp2025.mlswebsite.dao.HibernateDao;
import org.derryfield.isp2025.mlswebsite.dao.MatchDao;
import org.derryfield.isp2025.mlswebsite.model.Match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Log4j2
@Repository
@Primary
@Transactional
public class MatchDao_MySql extends HibernateDao<Match, Long> implements MatchDao {

    @Autowired
    public MatchDao_MySql(EntityManager entityManager) {
        super(entityManager, Match.class);
    }

    @Override
    public List<Match> getMatchesForTeam(String team) {
        CriteriaQuery<Match> query = createCriteriaQuery();
        Root<Match> root = query.from(Match.class);
        query.select(root)
                .where(getCriteriaBuilder().or(
                        getCriteriaBuilder().equal(root.get("homeTeam"), team),
                        getCriteriaBuilder().equal(root.get("awayTeam"), team)
                ))
                .orderBy(
                        getCriteriaBuilder().desc(root.get("matchDate")),
                        getCriteriaBuilder().desc(root.get("matchTime"))
                );
        return executeQuery(query);
    }

    @Override
    public List<Match> getMatchupHistoryForTeams(String teamA, String teamB) {
        CriteriaQuery<Match> query = createCriteriaQuery();
        Root<Match> root = query.from(Match.class);
        query.select(root)
                .where(getCriteriaBuilder().or(
                        getCriteriaBuilder().and(
                                getCriteriaBuilder().equal(root.get("homeTeam"), teamA),
                                getCriteriaBuilder().equal(root.get("awayTeam"), teamB)
                        ),
                        getCriteriaBuilder().and(
                                getCriteriaBuilder().equal(root.get("homeTeam"), teamB),
                                getCriteriaBuilder().equal(root.get("awayTeam"), teamA)
                        )
                ))
                .orderBy(
                        getCriteriaBuilder().desc(root.get("matchDate")),
                        getCriteriaBuilder().desc(root.get("matchTime"))
                );
        return executeQuery(query);
    }

    @Override
    public List<Match> getMatchHistoryForCompetiton(String competiton) {
        CriteriaQuery<Match> query = createCriteriaQuery();
        Root<Match> root = query.from(Match.class);
        query.select(root)
                .where(getCriteriaBuilder().equal(root.get("competition"), competiton))
                .orderBy(
                        getCriteriaBuilder().desc(root.get("matchDate")),
                        getCriteriaBuilder().desc(root.get("matchTime"))
                );
        return executeQuery(query);
    }
}
