package com.andrew.pokemon.application.port.in;

import com.andrew.pokemon.domain.Pokemon;
import java.util.List;
import java.util.Optional;

public interface PokemonUseCase {
    Pokemon create(Pokemon pokemon);
    List<Pokemon> findAll();
    Optional<Pokemon> findById(Long id);
    Optional<Pokemon> update(Long id, Pokemon pokemon);
    void delete(Long id);
}
