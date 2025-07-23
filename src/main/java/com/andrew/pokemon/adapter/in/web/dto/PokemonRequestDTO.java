package com.andrew.pokemon.adapter.in.web.dto;

import com.andrew.pokemon.domain.Pokemon;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PokemonRequestDTO {
    private String name;
    private String type;
    private List<String> attacks;
    private int level;
    private int pokedexNumber;

    public Pokemon toDomain() {
        return new Pokemon(name, type, attacks, level, pokedexNumber);
    }
}