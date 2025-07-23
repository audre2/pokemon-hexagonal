package com.andrew.pokemon.adapter.out.persistence;

import com.andrew.pokemon.application.port.out.PokemonRepositoryPort;
import com.andrew.pokemon.domain.Pokemon;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PokemonPersistenceAdapter implements PokemonRepositoryPort {

    private final JpaPokemonRepository repository;

    public PokemonPersistenceAdapter(JpaPokemonRepository repository) {
        this.repository = repository;
    }

    @Override
    public Pokemon save(Pokemon pokemon) {
        return repository.save(new PokemonEntity(pokemon)).toDomain();
    }

    @Override
    public List<Pokemon> findAll() {
        return repository.findAll().stream()
                .map(PokemonEntity::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Pokemon> findById(Long id) {
        return repository.findById(id).map(PokemonEntity::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
