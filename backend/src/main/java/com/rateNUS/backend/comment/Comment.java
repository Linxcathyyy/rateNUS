package com.rateNUS.backend.comment;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "targetId", nullable = false, updatable = false)
    private long targetId;

    @Column(name = "rating", nullable = false)
    private double rating;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "timestamp", updatable = false)
    @CreationTimestamp
    private Timestamp timestamp;

    public Comment() {
    }

    public Comment(long targetId, double rating, String text, Type type) {
        this.targetId = targetId;
        this.rating = rating;
        this.text = text;
        this.type = type;
    }

    public Comment(long id, long targetId, double rating, String text, Type type) {
        this.id = id;
        this.targetId = targetId;
        this.rating = rating;
        this.text = text;
        this.type = type;
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Comment)) {
            return false;
        }

        Comment comment = (Comment) obj;
        return id == comment.id
                && targetId == comment.targetId
                && Double.compare(comment.rating, rating) == 0
                && Objects.equals(text, comment.text)
                && type == comment.type
                && Objects.equals(timestamp, comment.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, targetId, rating, text, type, timestamp);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", targetId=" + targetId +
                ", rating=" + rating +
                ", text='" + text + '\'' +
                ", type=" + type +
                ", timestamp=" + timestamp +
                '}';
    }

    public enum Type {
        hostel, canteen, studyArea
    }
}
