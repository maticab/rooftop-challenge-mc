package com.rooftopmc.challenge.response;

import lombok.Data;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Data
public class TextanalizerResponse {
    final String id;

    URI url;

    public TextanalizerResponse(String id) {
        this.id = id;
        this.url = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    }
}
