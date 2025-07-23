package com.andrew.pokemon.application.port.out;

import com.andrew.pokemon.domain.Pokemon;
import java.util.List;
import java.util.Optional;

public interface PokemonRepositoryPort {
    Pokemon save(Pokemon pokemon);
    List<Pokemon> findAll();
    Optional<Pokemon> findById(Long id);
    void deleteById(Long id);
}
