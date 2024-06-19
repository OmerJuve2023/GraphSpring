package com.omersolutions.graphspring.repository;

import com.omersolutions.graphspring.entities.Post;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface PostRepository extends ListCrudRepository<Post, Integer> {
    Optional<Post> findPostByUrlContainsIgnoreCase(String url);
}
