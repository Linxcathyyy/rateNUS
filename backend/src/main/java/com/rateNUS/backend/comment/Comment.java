package com.rateNUS.backend.comment;

import com.rateNUS.backend.util.Type;
import org.hibernate.annotations.UpdateTimestamp;

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

    @Column(name = "type", nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "timestamp")
    @UpdateTimestamp
    private Timestamp timestamp;

    public Comment() {}

    // For dummy data
    public Comment(long targetId, long userId, int rating, String text, Type type) {
        this.targetId = targetId;
        this.userId = userId;
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

    public long getTargetId() {
        return targetId;
    }

    public long getUserId() {
        return userId;
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

    public Timestamp getTimestamp() {
        return timestamp;
    }
}
