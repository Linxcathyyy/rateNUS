package com.rateNUS.backend.exception;

public class InvalidInputException extends IllegalStateException {
    public InvalidInputException(String message) {
        super(message);
    }
}
