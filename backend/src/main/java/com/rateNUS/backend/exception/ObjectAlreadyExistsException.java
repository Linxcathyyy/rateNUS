package com.rateNUS.backend.exception;

import java.util.HashMap;

import com.rateNUS.backend.util.Type;

public class ObjectAlreadyExistsException extends IllegalArgumentException {
    private static final String format = "%s with %s already exists.";

    public ObjectAlreadyExistsException(Type type, String... conditions) {
        super(String.format(format, type, conditions));
    }
}
