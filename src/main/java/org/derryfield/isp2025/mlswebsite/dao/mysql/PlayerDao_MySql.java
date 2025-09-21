package org.derryfield.isp2025.mlswebsite.dao.mysql;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.derryfield.isp2025.mlswebsite.dao.HibernateDao;
import org.derryfield.isp2025.mlswebsite.dao.PlayerDao;
import org.derryfield.isp2025.mlswebsite.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Log4j2
@Repository
@Primary
@Transactional
public class PlayerDao_MySql extends HibernateDao<Player, Long> implements PlayerDao {

    @Autowired
    public PlayerDao_MySql(EntityManager entityManager) {
        super(entityManager, Player.class);
    }

    @Override
    public List<Player> findByCurrentTeam(String currentTeam) {
        CriteriaQuery<Player> query = createCriteriaQuery();
        Root<Player> root = query.from(Player.class);
        query.select(root)
                .where(getCriteriaBuilder().equal(root.get("currentTeam"), currentTeam))
                .orderBy(getCriteriaBuilder().asc(root.get("name")));
        return executeQuery(query);
    }

    @Override
    public List<Player> findByCountry(String country) {
        CriteriaQuery<Player> query = createCriteriaQuery();
        Root<Player> root = query.from(Player.class);
        query.select(root)
                .where(getCriteriaBuilder().equal(root.get("country"), country))
                .orderBy(getCriteriaBuilder().asc(root.get("name")));
        return executeQuery(query);
    }
}