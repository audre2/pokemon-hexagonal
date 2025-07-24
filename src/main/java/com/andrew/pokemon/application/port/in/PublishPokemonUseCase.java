package com.andrew.pokemon.application.port.in;

import com.andrew.pokemon.domain.Pokemon;

public interface PublishPokemonUseCase {
    void publishAsync(Pokemon pokemon);
}
