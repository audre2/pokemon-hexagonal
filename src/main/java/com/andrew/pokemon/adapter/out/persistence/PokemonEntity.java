package com.andrew.pokemon.adapter.out.persistence;

import com.andrew.pokemon.domain.Pokemon;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PokemonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;

    @ElementCollection
    private List<String> attacks;

    private int level;
    private int pokedexNumber;

    public PokemonEntity(Pokemon pokemon) {
        this.id = pokemon.getId();
        this.name = pokemon.getName();
        this.type = pokemon.getType();
        this.attacks = pokemon.getAttacks();
        this.level = pokemon.getLevel();
        this.pokedexNumber = pokemon.getPokedexNumber();
    }

    public Pokemon toDomain() {
        return new Pokemon(id, name, type, attacks, level, pokedexNumber);
    }
}