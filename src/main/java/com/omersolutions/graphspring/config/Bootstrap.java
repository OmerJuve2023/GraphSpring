package com.omersolutions.graphspring.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.omersolutions.graphspring.repository.PostRepository;
import com.omersolutions.graphspring.dto.Posts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.asm.TypeReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
public class Bootstrap implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(Bootstrap.class);
    private final ObjectMapper objectMapper;
    private final PostRepository postRepository;

    public Bootstrap(ObjectMapper objectMapper, PostRepository postRepository) {
        this.objectMapper = objectMapper;
        this.postRepository = postRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (postRepository.count() == 0) {
            try (InputStream inputStream = TypeReference.class.getResourceAsStream("/data/post.json")) {
                Posts post = objectMapper.readValue(inputStream, Posts.class);
                log.info("Reading {} data from data.json", post.posts().size());
                postRepository.saveAll(post.posts());
                log.info("Data loaded");
            } catch (Exception e) {
                log.error("Error loading data", e);
            }
        }
    }
}