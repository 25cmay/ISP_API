package org.derryfield.isp2025.mlswebsite.service;

import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.derryfield.isp2025.mlswebsite.dao.ContractDao;
import org.derryfield.isp2025.mlswebsite.model.Contract;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class ContractService {

    private final ContractDao dao;

    @Transactional
    public Contract createContract(@NonNull @Validated final Contract contract) {
        log.info("Creating contract: {}", contract);
        return dao.save(contract);
    }

    @Transactional
    public Optional<Contract> updateContract(@NonNull final Long id, @NonNull @Validated final Contract updatedContract) {
        return dao.findById(id).map(existing -> {
            existing.setTeam(updatedContract.getTeam());
            existing.setPlayer(updatedContract.getPlayer());
            existing.setAcquisitionMethod(updatedContract.getAcquisitionMethod());
            existing.setTeamAquiredFrom(updatedContract.getTeamAquiredFrom());
            existing.setDateSigned(updatedContract.getDateSigned());
            existing.setContractEnd(updatedContract.getContractEnd());
            existing.setContractOptions(updatedContract.getContractOptions());
            existing.setWage(updatedContract.getWage());
            existing.setCapHit(updatedContract.getCapHit());
            existing.setGeneralAllocationMoneyUsed(updatedContract.getGeneralAllocationMoneyUsed());
            return dao.save(existing);
        });
    }

    @Transactional
    public boolean deleteContract(@NonNull final Long id) {
        return dao.findById(id).map(contract -> {
            dao.delete(contract);
            return true;
        }).orElse(false);
    }

    public Optional<Contract> getContractById(@NonNull final Long id) {
        return dao.findById(id);
    }

    public List<Contract> getContractsByTeam(@NonNull final Long teamId) {
        return dao.findByTeamId(teamId);
    }

    public List<Contract> getContractsByPlayer(@NonNull final Long playerId) {
        return dao.findByPlayerId(playerId);
    }
}
