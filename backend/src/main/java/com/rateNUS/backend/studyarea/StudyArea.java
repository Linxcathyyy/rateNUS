package com.rateNUS.backend.studyarea;

import com.rateNUS.backend.util.CommentUtil;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table
public class StudyArea {
    @Id
    @SequenceGenerator(name = "study_area_sequence", sequenceName = "study_area_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "study_area_sequence")
    @Column(name = "id", updatable = false)
    private long id;

    @Column(name = "name", nullable = false, columnDefinition = "TEXT")
    private String name;

    @Column(name = "rating")
    private double rating;

    @Column(name = "commentCount")
    private int commentCount;

    @Column(name = "location", nullable = false, columnDefinition = "TEXT")
    private String location;

    @ElementCollection
    @JoinTable(name = "studyAreaImages", joinColumns = @JoinColumn(name = "study_area_id"))
    @Column(name = "imageUrl", nullable = false)
    private List<String> imageUrl;

    public StudyArea() {
    }

    // For dummy data
    public StudyArea(String name, String location, List<String> imageUrl) {
        this.name = name;
        this.location = location;
        this.imageUrl = imageUrl;
    }

    // For StudyAreaRepository
    public StudyArea(long id, String name, double rating, int commentCount, String location, List<String> imageUrl) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.commentCount = commentCount;
        this.location = location;
        this.imageUrl = imageUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<String> getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(List<String> imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void addComment(int newRating) {
        rating = CommentUtil.addComment(rating, commentCount, newRating);
        commentCount++;
    }

    public void updateComment(int oldRating, int newRating) {
        rating = CommentUtil.updateComment(rating, commentCount, oldRating, newRating);
    }

    public void deleteComment(int ratingToRemove) {
        rating = CommentUtil.deleteComment(rating, commentCount, ratingToRemove);
        commentCount--;
    }
}
