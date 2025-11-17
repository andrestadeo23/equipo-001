package com.bytescolaborativos.eq01.recommendation_engine.controller;

import com.bytescolaborativos.eq01.recommendation_engine.dto.TournamentDTO;
import com.bytescolaborativos.eq01.recommendation_engine.service.TournamentService;
import jakarta.validation.Constraint;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tournaments")
@AllArgsConstructor
public class TournamentController {

    private final TournamentService tournamentService;

    /*public TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }*/

    @PostMapping
    public ResponseEntity<TournamentDTO> create(@Valid @RequestBody TournamentDTO dto) {
        TournamentDTO created = tournamentService.create(dto);

        return ResponseEntity
                .created(URI.create("/api/tournaments")) // opcional: agregar id si lo tienes en el DTO
                .body(created);
    }

    @GetMapping
    public ResponseEntity<Page<TournamentDTO>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<TournamentDTO> result = tournamentService.findAll(page, size);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TournamentDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(tournamentService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TournamentDTO> update(@PathVariable UUID id,
                                                @Valid @RequestBody TournamentDTO dto) {
        return ResponseEntity.ok(tournamentService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        tournamentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}