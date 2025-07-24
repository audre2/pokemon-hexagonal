package com.andrew.pokemon.application.port.out;

import com.andrew.pokemon.domain.Pokemon;

public interface PokemonPublisherPort {
    void publish(Pokemon pokemon);
}
