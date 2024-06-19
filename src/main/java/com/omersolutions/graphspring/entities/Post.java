package com.omersolutions.graphspring.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import java.time.LocalDateTime;

public record Post(
        @Id
        Integer id,
        String title,
        String summary,
        String url,
        @JsonProperty("date_published")
        LocalDateTime datePublished,
        @Version
        Integer version
) {
}
