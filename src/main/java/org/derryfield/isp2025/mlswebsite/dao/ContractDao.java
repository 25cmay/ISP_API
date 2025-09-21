package org.derryfield.isp2025.mlswebsite.dao;

import org.derryfield.isp2025.mlswebsite.model.Contract;
import java.util.List;

public interface ContractDao extends EntityDao<Contract, Long> {
    List<Contract> findByTeamId(Long teamId);
    List<Contract> findByPlayerId(Long playerId);
}