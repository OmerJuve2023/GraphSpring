package com.omersolutions.graphspring.controller;

import com.omersolutions.graphspring.entities.Post;
import com.omersolutions.graphspring.repository.PostRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/posts")
public class PostController {
    private final PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping("/")
    List<Post> getPosts() {
        return postRepository.findAll();
    }

    @GetMapping("/{id}")
    Optional<Post> getPostById(@PathVariable Integer id) {
        return postRepository.findById(id);
    }

    @GetMapping("/slug/{slug}")
    Optional<Post> findBySlug(@PathVariable String slug) {
        return postRepository.findPostByUrlContainsIgnoreCase(slug);
    }

    List<Post> findAllPostWithCommentsAndRelatedPosts() {
        return null;
    }
}