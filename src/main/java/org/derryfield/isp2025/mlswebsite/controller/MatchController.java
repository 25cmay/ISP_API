package org.derryfield.isp2025.mlswebsite.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.derryfield.isp2025.mlswebsite.model.Match;
import org.derryfield.isp2025.mlswebsite.service.MatchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/matches")
@RequiredArgsConstructor
public class MatchController {

    private final MatchService service;

    // Create a new match
    @PostMapping
    public ResponseEntity<Match> createMatch(@RequestBody @Validated final Match match) {
        Match createdMatch = service.createMatch(match);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMatch);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Match> getMatchById(@PathVariable final Long id) {
        return service.getMatchById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update an existing match
    @PutMapping("/{id}")
    public ResponseEntity<Match> updateMatch(@PathVariable Long id, @RequestBody @Validated final Match match) {
        Optional<Match> updated = service.updateMatch(id, match);
        return updated.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete a match
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatch(@PathVariable Long id) {
        boolean deleted = service.deleteMatch(id);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/team/{team}")
    public List<Match> getMatchesForTeam(@PathVariable final String team) {
        return service.getMatchesForTeam(team);
    }

    @GetMapping("/competition/{competition}")
    public List<Match> getMatchesForCompetition(@PathVariable final String competition) {
        return service.getMatchesForCompetition(competition);
    }
}

