package com.bytescolaborativos.eq01.recommendation_engine.service;

import com.bytescolaborativos.eq01.recommendation_engine.dto.TournamentDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface TournamentService {
    TournamentDTO create(TournamentDTO dto);

    Page<TournamentDTO> findAll(int page, int size);

    TournamentDTO findById(UUID id);

    TournamentDTO update(UUID id, TournamentDTO dto);

    void delete(UUID id);
}
