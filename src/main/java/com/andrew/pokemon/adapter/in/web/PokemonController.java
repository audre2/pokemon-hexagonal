package com.andrew.pokemon.adapter.in.web;

import com.andrew.pokemon.adapter.in.web.dto.PokemonRequestDTO;
import com.andrew.pokemon.adapter.in.web.dto.PokemonResponseDTO;
import com.andrew.pokemon.application.port.in.PokemonUseCase;
import com.andrew.pokemon.application.port.in.PublishPokemonUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pokemon")
@RequiredArgsConstructor
@Slf4j
public class PokemonController {

    private final PokemonUseCase service;
    private final PublishPokemonUseCase publishPokemonUseCase;

    @PostMapping
    public ResponseEntity<PokemonResponseDTO> create(@RequestBody PokemonRequestDTO dto) {
        return ResponseEntity.ok(new PokemonResponseDTO(service.create(dto.toDomain())));
    }

    @PostMapping("/async")
    public ResponseEntity<String> saveAsync(@RequestBody PokemonRequestDTO dto) {
        log.info("Received async request for Pokemon: {}", dto.getName());
        publishPokemonUseCase.publishAsync(dto.toDomain());
        return ResponseEntity.accepted().body("Pokemon enviado para Pub/Sub");
    }

    @GetMapping
    public List<PokemonResponseDTO> getAll() {
        return service.findAll().stream()
                .map(PokemonResponseDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        var optionalPokemon = service.findById(id);
        if (optionalPokemon.isPresent()) {
            return ResponseEntity.ok(new PokemonResponseDTO(optionalPokemon.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", "Pokemon not found with id " + id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody PokemonRequestDTO dto) {
        var optionalUpdated = service.update(id, dto.toDomain());
        if (optionalUpdated.isPresent()) {
            return ResponseEntity.ok(new PokemonResponseDTO(optionalUpdated.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", "Pokemon not found with id " + id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
