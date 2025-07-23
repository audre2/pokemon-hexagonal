package com.andrew.pokemon.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPokemonRepository extends JpaRepository<PokemonEntity, Long> {
}
