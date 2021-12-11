package com.rateNUS.backend.exception;

public class PageOutOfBoundException extends IllegalStateException {
    private static final String format = "Requested page %d is out of bound!";

    public PageOutOfBoundException(int pageNum) {
        super(String.format(format, pageNum));
    }
}
