package com.portal.animals.exceptions;

public class AnimalNotFound extends RuntimeException {

    public AnimalNotFound(String message) {
        super(message);
    }
}
