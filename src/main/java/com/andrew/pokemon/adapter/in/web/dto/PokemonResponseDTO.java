package com.andrew.pokemon.adapter.in.web.dto;

import com.andrew.pokemon.domain.Pokemon;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PokemonResponseDTO {
    private Long id;
    private String name;
    private String type;
    private List<String> attacks;
    private int level;
    private int pokedexNumber;

    public PokemonResponseDTO(Pokemon pokemon) {
        this.id = pokemon.getId();
        this.name = pokemon.getName();
        this.type = pokemon.getType();
        this.attacks = pokemon.getAttacks();
        this.level = pokemon.getLevel();
        this.pokedexNumber = pokemon.getPokedexNumber();
    }
}
