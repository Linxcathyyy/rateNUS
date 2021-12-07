package com.rateNUS.backend.comment;

public class Comment<T> {
    private long id;

    // Id of the target (type T).
    private long targetId;
    private double rating;
    private String text;

    public Comment() {
    }

    public Comment(long id, long targetId, double rating, String text) {
        this.id = id;
        this.targetId = targetId;
        this.rating = rating;
        this.text = text;
    }

    public Comment(long targetId, double rating, String text) {
        this.targetId = targetId;
        this.rating = rating;
        this.text = text;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTargetId() {
        return targetId;
    }

    public void setTargetId(long targetId) {
        this.targetId = targetId;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", targetId=" + targetId +
                ", rating=" + rating +
                ", text='" + text + '\'' +
                '}';
    }
}
