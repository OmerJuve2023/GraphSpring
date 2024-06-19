package com.omersolutions.graphspring.entities;

public record Comment(Integer id, Integer postId, String name, String email, String body) {
}
