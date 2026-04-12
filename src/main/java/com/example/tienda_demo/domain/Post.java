package com.example.tienda_demo.domain;

import lombok.Data;
import lombok.NonNull;

@Data

public class Post {

    private Long id;
    @NonNull
    private String message;
    @NonNull
    private Long personId;

    public Post() {}

    public Post(Long id, String message, Long personId) {
        this.id = id;
        this.message = message;
        this.personId = personId;
    }
}
