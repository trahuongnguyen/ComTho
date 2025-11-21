package com.example.be_restaurant.exception;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {
    private final String key;

    public NotFoundException(String key, String message) {
        super(message);
        this.key = key;
    }
}
