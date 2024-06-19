package com.omersolutions.graphspring.controller;

import com.omersolutions.graphspring.entities.Comment;
import com.omersolutions.graphspring.entities.Post;
import com.omersolutions.graphspring.repository.PostRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class PostGraphQlControllerTest {

    @Mock
    private PostRepository postRepository;
    @Mock
    private RestClient restClient;

    private PostGraphQlController postGraphQlController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        postGraphQlController = new PostGraphQlController(postRepository);
    }

    @Test
    void findAll() {
        Post post = new Post(1,
                "title",
                "body",
                "slug",
                LocalDate.now().atStartOfDay(),
                1);
        when(postRepository.findAll()).thenReturn(List.of(post));
        List<Post> result = postGraphQlController.findAll();
        assertEquals(1, result.size());
        assertEquals(post, result.get(0));
    }

    @Test
    void findPostById() {
        Post post = new Post(1,
                "title",
                "body",
                "slug",
                LocalDate.now().atStartOfDay(),
                1);
        when(postRepository.findById(1)).thenReturn(Optional.of(post));
        Optional<Post> result = postGraphQlController.findPostById(1);
        assertEquals(Optional.of(post), result);
    }

    @Test
    void findPostBySlug() {
        Post post = new Post(1,
                "title",
                "body",
                "slug",
                LocalDate.now().atStartOfDay(),
                1);
        when(postRepository.findPostByUrlContainsIgnoreCase("slug"))
                .thenReturn(Optional.of(post));
        Optional<Post> result = postGraphQlController.findPostBySlug("slug");
        assertEquals(Optional.of(post), result);
    }

}