package com.example.week08.domain;

import lombok.Getter;

@Getter
public enum TypeLike {
    POST("POST"),COMMENT("COMMENT");
    private final String value;
    TypeLike(String comment) {
        this.value = comment;
    }
}
