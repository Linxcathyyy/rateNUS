package com.rateNUS.backend.exception;

public class HostelNotFoundException extends IllegalStateException {
    private static final String format = "Hostel with ID %d does not exist.";

    public HostelNotFoundException(long hostelId) {
        super(String.format(format, hostelId));
    }
}
