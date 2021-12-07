package com.rateNUS.backend.comment;

public class Comment<T> {
    private long id;
    /** Id of the target (type T). */
    private long targetId;
//    private long userId;
    private double rating;
    private String text;
//    private ?? image;
}
