package org.derryfield.isp2025.mlswebsite.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.derryfield.isp2025.mlswebsite.model.Player;
import org.derryfield.isp2025.mlswebsite.service.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/players")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService service;

    // Create a new player
    @PostMapping
    public ResponseEntity<Player> createPlayer(@RequestBody @Validated final Player player) {
        Player createdPlayer = service.createPlayer(player);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPlayer);
    }

    // Get player by ID
    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable final Long id) {
        return service.getPlayerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update an existing player
    @PutMapping("/{id}")
    public ResponseEntity<Player> updatePlayer(@PathVariable Long id, @RequestBody @Validated final Player player) {
        Optional<Player> updated = service.updatePlayer(id, player);
        return updated.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete a player
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable Long id) {
        boolean deleted = service.deletePlayer(id);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    // Get all players from a team
    @GetMapping("/team/{teamName}")
    public List<Player> getPlayersByTeam(@PathVariable final String teamName) {
        return service.getPlayersByTeam(teamName);
    }

    // Get all players by nationality
    @GetMapping("/country/{country}")
    public List<Player> getPlayersByCountry(@PathVariable final String country) {
        return service.getPlayersByCountry(country);
    }
}