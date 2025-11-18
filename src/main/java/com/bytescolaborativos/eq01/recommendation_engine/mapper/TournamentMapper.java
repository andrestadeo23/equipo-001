package com.bytescolaborativos.eq01.recommendation_engine.mapper;

import com.bytescolaborativos.eq01.recommendation_engine.dto.TournamentDTO;
import com.bytescolaborativos.eq01.recommendation_engine.model.Tournament;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TournamentMapper {

    Tournament toEntity(TournamentDTO dto);

    TournamentDTO toDto(Tournament entity);

    void updateEntityFromDto(TournamentDTO dto, @MappingTarget Tournament entity);
}
