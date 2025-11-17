package com.bytescolaborativos.eq01.recommendation_engine.repository;


import com.bytescolaborativos.eq01.recommendation_engine.model.Tournament;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, UUID>{
    // Para paginado sin filtros
    Page<Tournament> findAll(Pageable pageable);

    // para paginado con filtro por status opcional
    Page<Tournament> findByStatusIgnoreCase(String status, Pageable pageable);
}