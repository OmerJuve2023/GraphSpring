package com.omersolutions.graphspring.controller;

import com.omersolutions.graphspring.entities.Comment;
import com.omersolutions.graphspring.entities.Post;
import com.omersolutions.graphspring.repository.PostRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Controller
public class PostGraphQlController {
    private static final Logger log = Logger.getLogger(PostGraphQlController.class.getName());
    private final PostRepository postRepository;
    private final RestClient restClient;

    public PostGraphQlController(PostRepository postRepository) {
        this.postRepository = postRepository;
        this.restClient = RestClient.create("https://jsonplaceholder.typicode.com/");
    }

    @SchemaMapping(typeName = "Query", field = "findAllPosts")
    List<Post> findAll() {
        return postRepository.findAll();
    }

    @QueryMapping
    Optional<Post> findPostById(@Argument Integer id) {
        return postRepository.findById(id);
    }

    @QueryMapping
    Optional<Post> findPostBySlug(@Argument String slug) {
        return postRepository.findPostByUrlContainsIgnoreCase(slug);
    }

    @SchemaMapping
    List<Comment> comments(Post post) {
        log.info("Fetching comments for post: '{}'" + post.title());
        List<Comment> allComments = restClient
                .get()
                .uri("/comments")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
        assert allComments != null;
        return allComments.stream()
                .filter(comment -> comment.postId().equals(post.id())).collect(Collectors.toList());
    }
}