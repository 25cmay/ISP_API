package org.derryfield.isp2025.mlswebsite.dao.mysql;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.derryfield.isp2025.mlswebsite.dao.HibernateDao;
import org.derryfield.isp2025.mlswebsite.dao.TeamDao;
import org.derryfield.isp2025.mlswebsite.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Log4j2
@Repository
@Primary
@Transactional
public class TeamDao_MySql extends HibernateDao<Team, Long> implements TeamDao {

    @Autowired
    public TeamDao_MySql(EntityManager entityManager) {
        super(entityManager, Team.class);
    }

    @Override
    public List<Team> findByBuild(String rosterBuild) {
        CriteriaQuery<Team> query = createCriteriaQuery();
        Root<Team> root = query.from(Team.class);
        query.select(root)
                .where(getCriteriaBuilder().equal(root.get("build"), rosterBuild))
                .orderBy(getCriteriaBuilder().asc(root.get("name")));
        return executeQuery(query);
    }
}