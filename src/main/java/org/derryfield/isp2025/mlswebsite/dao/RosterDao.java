package org.derryfield.isp2025.mlswebsite.dao;

import org.derryfield.isp2025.mlswebsite.model.Roster;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface RosterDao extends EntityDao<Roster, Long> {
    // updates a roster in the dao
    List<Roster> findBySeason(String season);
    List<Roster> findByTeam(String team);
}