package com.andrew.pokemon.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pokemon {
    private Long id;
    private String name;
    private String type;
    private List<String> attacks;
    private int level;
    private int pokedexNumber;

    public Pokemon(String name, String type, List<String> attacks, int level, int pokedexNumber) {
        this.name = name;
        this.type = type;
        this.attacks = attacks;
        this.level = level;
        this.pokedexNumber = pokedexNumber;
    }
}