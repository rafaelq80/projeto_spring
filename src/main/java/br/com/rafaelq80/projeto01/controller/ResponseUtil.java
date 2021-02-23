package br.com.rafaelq80.projeto01.controller;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import java.net.URI;
import java.util.Objects;
import java.util.Optional;

public class ResponseUtil {
    static <T> URI resourceUri(T resourceId) {
        Objects.requireNonNull(resourceId);
        return Optional.of(resourceId).map(id -> fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri())
                .orElseThrow(IllegalArgumentException::new);
    }
}