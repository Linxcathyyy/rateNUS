package com.rateNUS.backend.exception;

import com.rateNUS.backend.util.Type;

public class TypeNotFoundException extends IllegalStateException {
    private static final String format = "%s with ID %d does not exist.";

    public TypeNotFoundException(Type type, long id) {
        super(String.format(format, type, id));
    }
}
