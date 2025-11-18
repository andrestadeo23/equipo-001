package com.bytescolaborativos.eq01.recommendation_engine.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TournamentException extends RuntimeException{
    public TournamentException(UUID id) {
        super("Tournament not found with id: " + id);
    }
}
