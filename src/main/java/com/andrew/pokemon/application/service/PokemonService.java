package com.andrew.pokemon.application.service;

import com.andrew.pokemon.application.port.in.PokemonUseCase;
import com.andrew.pokemon.application.port.out.PokemonRepositoryPort;
import com.andrew.pokemon.domain.Pokemon;
import com.andrew.pokemon.exception.PokemonNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PokemonService implements PokemonUseCase {

    private final PokemonRepositoryPort repository;

    @Override
    public Pokemon create(Pokemon pokemon) {
        log.info("Creating Pokemon: {}", pokemon.getName());
        Pokemon saved = repository.save(pokemon);
        log.info("Pokemon saved with ID: {}", saved.getId());
        return saved;
    }

    @Override
    public List<Pokemon> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Pokemon> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Pokemon> update(Long id, Pokemon pokemon) {
        return repository.findById(id).map(existing -> {
            existing.setName(pokemon.getName());
            existing.setType(pokemon.getType());
            existing.setAttacks(pokemon.getAttacks());
            existing.setLevel(pokemon.getLevel());
            existing.setPokedexNumber(pokemon.getPokedexNumber());
            return repository.save(existing);
        });
    }

    @Override
    public void delete(Long id) {
        if (repository.findById(id).isEmpty()) {
            throw new PokemonNotFoundException("Pokemon not found with id " + id);
        }
        repository.deleteById(id);
    }
}
