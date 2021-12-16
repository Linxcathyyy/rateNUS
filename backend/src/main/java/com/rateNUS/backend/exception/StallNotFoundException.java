package com.rateNUS.backend.exception;

public class StallNotFoundException extends IllegalStateException {
    private static final String format = "Stall with ID %d does not exist.";

    public StallNotFoundException(long stallId) {
        super(String.format(format, stallId));
    }
}
