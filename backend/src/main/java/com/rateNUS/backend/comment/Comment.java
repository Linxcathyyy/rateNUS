package com.rateNUS.backend.comment;

import com.rateNUS.backend.util.Type;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table
public class Comment {
    @Id
    @SequenceGenerator(name = "comment_sequence", sequenceName = "comment_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_sequence")
    @Column(name = "id", updatable = false)
    private long id;

    @Column(name = "targetId", nullable = false, updatable = false)
    private long targetId;

    @Column(name = "userId", nullable = false, updatable = false)
    private long userId;

    @Column(name = "rating", nullable = false)
    private int rating;

    @Column(name = "text", nullable = false, columnDefinition = "TEXT")
    private String text;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "timestamp", updatable = false)
    @CreationTimestamp
    private Timestamp timestamp;

    public Comment() {}

    // For dummy data
    public Comment(long targetId, int rating, String text, Type type) {
        this.targetId = targetId;
        this.rating = rating;
        this.text = text;
        this.type = type;
    }

    // For CommentRepository
    public Comment(long id, long targetId, long userId, int rating, String text, Type type) {
        this.id = id;
        this.targetId = targetId;
        this.userId = userId;
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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
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
}
