package org.derryfield.isp2025.mlswebsite.dao;

import org.derryfield.isp2025.mlswebsite.model.Team;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface TeamDao extends EntityDao<Team, Long> {
    // updates a team in the dao
    List<Team> findByBuild(String rosterBuild);
}