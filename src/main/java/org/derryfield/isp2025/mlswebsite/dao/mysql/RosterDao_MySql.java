package org.derryfield.isp2025.mlswebsite.dao.mysql;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.derryfield.isp2025.mlswebsite.dao.HibernateDao;
import org.derryfield.isp2025.mlswebsite.dao.RosterDao;
import org.derryfield.isp2025.mlswebsite.model.Roster;
import org.derryfield.isp2025.mlswebsite.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Log4j2
@Repository
@Primary
@Transactional
public class RosterDao_MySql extends HibernateDao<Roster, Long> implements RosterDao {

    @Autowired
    public RosterDao_MySql(EntityManager entityManager) {
        super(entityManager, Roster.class);
    }

    @Override
    public List<Roster> findBySeason(String season) {
        CriteriaQuery<Roster> query = createCriteriaQuery();
        Root<Roster> root = query.from(Roster.class);
        query.select(root)
             .where(getCriteriaBuilder().equal(root.get("season"), season))
             .orderBy(getCriteriaBuilder().asc(root.get("rosterNumber")));
        return executeQuery(query);
    }

    @Override
    public List<Roster> findByTeam(String team) {
        CriteriaQuery<Roster> query = createCriteriaQuery();
        Root<Roster> root = query.from(Roster.class);
        Join<Roster, Team> teamJoin = root.join("team");

        query.select(root)
             .where(getCriteriaBuilder().equal(teamJoin.get("name"), team))
             .orderBy(getCriteriaBuilder().asc(root.get("rosterNumber")));
        return executeQuery(query);
    }
}