package com.andrew.pokemon.application.service;

import com.andrew.pokemon.application.port.in.PublishPokemonUseCase;
import com.andrew.pokemon.application.port.out.PokemonPublisherPort;
import com.andrew.pokemon.domain.Pokemon;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PublishPokemonService implements PublishPokemonUseCase {

    private final PokemonPublisherPort publisher;

    @Override
    public void publishAsync(Pokemon pokemon) {
        publisher.publish(pokemon);
    }
}
