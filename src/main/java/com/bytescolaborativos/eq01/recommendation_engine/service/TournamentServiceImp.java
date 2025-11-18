package com.bytescolaborativos.eq01.recommendation_engine.service;

import com.bytescolaborativos.eq01.recommendation_engine.dto.TournamentDTO;
import com.bytescolaborativos.eq01.recommendation_engine.model.Tournament;
import com.bytescolaborativos.eq01.recommendation_engine.exceptions.TournamentException;
import com.bytescolaborativos.eq01.recommendation_engine.mapper.TournamentMapper;
import com.bytescolaborativos.eq01.recommendation_engine.repository.TournamentRepository;
import com.bytescolaborativos.eq01.recommendation_engine.service.TournamentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TournamentServiceImp implements TournamentService {
    private final  TournamentRepository tournamentRepository;
    private final TournamentMapper tournamentMapper;

    @Override
    public TournamentDTO create(TournamentDTO dto) {
        Tournament entity = tournamentMapper.toEntity(dto);
        Tournament saved = tournamentRepository.save(entity);
        return tournamentMapper.toDto(saved);
    }

    @Override
    public Page<TournamentDTO> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Tournament> resultPage;

        resultPage = tournamentRepository.findAll(pageable);

        return resultPage.map(tournamentMapper::toDto);
    }

    @Override
    public TournamentDTO findById(UUID id) {
        Tournament entity = tournamentRepository.findById(id)
                .orElseThrow(() -> new TournamentException(id));
        return tournamentMapper.toDto(entity);
    }

    @Override
    public TournamentDTO update(UUID id, TournamentDTO dto) {
        Tournament entity = tournamentRepository.findById(id)
                .orElseThrow(() -> new TournamentException(id));

        // Aquí MapStruct copia name, startDate, endDate, status
        tournamentMapper.updateEntityFromDto(dto, entity);

        Tournament updated = tournamentRepository.save(entity);
        return tournamentMapper.toDto(updated);
    }

    @Override
    public void delete(UUID id) {
        if (!tournamentRepository.existsById(id)) {
            throw new TournamentException(id);
        }
        tournamentRepository.deleteById(id);
    }

}
