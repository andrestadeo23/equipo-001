package com.bytescolaborativos.eq01.recommendation_engine.controller;

import com.bytescolaborativos.eq01.recommendation_engine.dto.TournamentDTO;
import com.bytescolaborativos.eq01.recommendation_engine.service.TournamentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
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

    @PostMapping
    @Operation(
            summary = "Crea un nuevo torneo",
            description = "Realiza el registro de un torneo"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Torneo creado"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "409", description = "Torneo duplicado"),
            @ApiResponse(responseCode = "403", description = "No autorizado para crear torneo"),
    })
    public ResponseEntity<TournamentDTO> create(@Valid @RequestBody TournamentDTO dto) {
        TournamentDTO created = tournamentService.create(dto);

        return ResponseEntity
                .created(URI.create("/api/tournaments")) // opcional: agregar id si lo tienes en el DTO
                .body(created);
    }

    @GetMapping
    @Operation(
            summary = "Obtiene el listado de torneos",
            description = "Devuelve los torneos registrados en el sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Torneos obtenidos correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TournamentDTO.class))),
            @ApiResponse(responseCode = "403", description = "No autorizado"),
    })
    public ResponseEntity<Page<TournamentDTO>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<TournamentDTO> result = tournamentService.findAll(page, size);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener el detalle de unntorneo por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Torneo encontrado"),
            @ApiResponse(responseCode = "404", description = "No existe el torneo"),
            @ApiResponse(responseCode = "403", description = "No autorizado para realizar esta consulta"),
    })
    public ResponseEntity<TournamentDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(tournamentService.findById(id));
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Actualizar los datos de un torneo existente",
            description = "Actualiza los datos de un torneo identificado por su ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Torneo actualizado correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TournamentDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Datos inválidos",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Torneo no encontrado",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "No tienes permisos para actualizar este recurso",
                    content = @Content
            )
    })
    public ResponseEntity<TournamentDTO> update(@PathVariable UUID id,
                                                @Valid @RequestBody TournamentDTO dto) {
        return ResponseEntity.ok(tournamentService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Eliminar un torneo",
            description = "Elimina un torneo identificado por su ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Torneo eliminado correctamente",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Torneo no encontrado",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "No tienes permisos para eliminar este recurso",
                    content = @Content
            )
    })
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        tournamentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}