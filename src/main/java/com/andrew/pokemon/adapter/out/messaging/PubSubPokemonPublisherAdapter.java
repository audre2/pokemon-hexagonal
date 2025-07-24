package com.andrew.pokemon.adapter.out.messaging;

import com.andrew.pokemon.application.port.out.PokemonPublisherPort;
import com.andrew.pokemon.domain.Pokemon;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.gson.Gson;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.ProjectTopicName;
import com.google.pubsub.v1.PubsubMessage;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class PubSubPokemonPublisherAdapter implements PokemonPublisherPort {

    @Value("${gcp.project-id}")
    private final String projectId;

    @Value("${gcp.pokemon-topic}")
    private final String topicId;

    private Publisher publisher;
    private final Gson gson = new Gson();

    @PostConstruct
    public void initPublisher() throws IOException {
        ProjectTopicName topicName = ProjectTopicName.of(projectId, topicId);
        this.publisher = Publisher.newBuilder(topicName).build();
        log.info("Pub/Sub Publisher initialized for topic: {}", topicId);
    }

    @Override
    public void publish(Pokemon pokemon) {
        try {
            String json = gson.toJson(pokemon);
            ByteString data = ByteString.copyFromUtf8(json);
            PubsubMessage message = PubsubMessage.newBuilder().setData(data).build();
            publisher.publish(message).get();  // espera confirmação
            log.debug("Published message for Pokemon: {}", pokemon.getName());
        } catch (Exception e) {
            log.error("Failed to publish Pokemon '{}' to Pub/Sub topic '{}'", pokemon.getName(), topicId, e);
            throw new RuntimeException("Error publishing to Pub/Sub: " + e.getMessage(), e);
        }
    }
}