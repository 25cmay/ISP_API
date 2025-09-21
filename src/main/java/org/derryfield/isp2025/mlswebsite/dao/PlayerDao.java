package org.derryfield.isp2025.mlswebsite.dao;

import org.derryfield.isp2025.mlswebsite.model.Player;
import java.util.List;

public interface PlayerDao extends EntityDao<Player, Long> {
    // Creates or updates a player in the dao
    
    List<Player> findByCurrentTeam(String currentTeam);
    List<Player> findByCountry(String country);
}
