package org.derryfield.isp2025.mlswebsite.dao.mysql;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.derryfield.isp2025.mlswebsite.dao.ContractDao;
import org.derryfield.isp2025.mlswebsite.dao.HibernateDao;
import org.derryfield.isp2025.mlswebsite.model.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Log4j2
@Repository
@Primary
@Transactional
public class ContractDao_MySql extends HibernateDao<Contract, Long> implements ContractDao {

    @Autowired
    public ContractDao_MySql(EntityManager entityManager) {
        super(entityManager, Contract.class);
    }

    @Override
    public List<Contract> findByTeamId(Long teamId) {
        CriteriaQuery<Contract> query = createCriteriaQuery();
        Root<Contract> root = query.from(Contract.class);
        query.select(root)
                .where(getCriteriaBuilder().equal(root.get("team").get("id"), teamId))
                .orderBy(getCriteriaBuilder().asc(root.get("dateSigned")));
        return executeQuery(query);
    }

    @Override
    public List<Contract> findByPlayerId(Long playerId) {
        CriteriaQuery<Contract> query = createCriteriaQuery();
        Root<Contract> root = query.from(Contract.class);
        query.select(root)
                .where(getCriteriaBuilder().equal(root.get("player").get("id"), playerId))
                .orderBy(getCriteriaBuilder().desc(root.get("contractEnd")));
        return executeQuery(query);
    }
}
