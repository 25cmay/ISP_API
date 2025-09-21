package org.derryfield.isp2025.mlswebsite.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.derryfield.isp2025.mlswebsite.model.Team;
import org.derryfield.isp2025.mlswebsite.service.TeamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/teams")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService service;

    // Create a new team
    @PostMapping
    public ResponseEntity<Team> createTeam(@RequestBody @Validated final Team team) {
        Team createdTeam = service.createTeam(team);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTeam);
    }

    // Get a team by ID
    @GetMapping("/{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable final Long id) {
        return service.getTeamById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update a team
    @PutMapping("/{id}")
    public ResponseEntity<Team> updateTeam(@PathVariable Long id, @RequestBody @Validated final Team team) {
        Optional<Team> updated = service.updateTeam(id, team);
        return updated.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete a team
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long id) {
        boolean deleted = service.deleteTeam(id);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    // Find teams by roster build DP or U22
    @GetMapping("/build/{rosterBuild}")
    public List<Team> getTeamsByBuild(@PathVariable final String rosterBuild) {
        return service.getTeamsByBuild(rosterBuild);
    }
}