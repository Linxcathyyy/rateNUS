package com.rateNUS.backend.stall;

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
public class Stall {
    @Id
    @SequenceGenerator(name = "stall_sequence", sequenceName = "stall_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stall_sequence")
    @Column(name = "id", updatable = false)
    private long id;

    @Column(name = "name", nullable = false, columnDefinition = "TEXT")
    private String name;

    @Column(name = "rating")
    private double rating = -1;

    @Column(name = "commentCount")
    private int commentCount;

    @Column(name = "location", nullable = false, columnDefinition = "TEXT")
    private String location;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @ElementCollection
    @JoinTable(name = "stallImages", joinColumns = @JoinColumn(name = "stall_id"))
    @Column(name = "imageUrl", nullable = false)
    private List<String> imageUrl;

    @Column(name = "lowestPrice", nullable = false)
    private double lowestPrice;

    @Column(name = "highestPrice", nullable = false)
    private double highestPrice;

    public Stall() {}

    // For dummy data
    public Stall(String name, String location, String description, List<String> imageUrl, double lowestPrice,
                 double highestPrice) {
        this.name = name;
        this.location = location;
        this.description = description;
        this.imageUrl = imageUrl;
        this.lowestPrice = lowestPrice;
        this.highestPrice = highestPrice;
    }

    // For StallRepository
    public Stall(long id, String name, double rating, int commentCount, String location, String description,
                 List<String> imageUrl, double lowestPrice, double highestPrice) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.commentCount = commentCount;
        this.location = location;
        this.description = description;
        this.imageUrl = imageUrl;
        this.lowestPrice = lowestPrice;
        this.highestPrice = highestPrice;
    }

    public long getId() {
        return id;
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

    public int getCommentCount() {
        return commentCount;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(List<String> imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getLowestPrice() {
        return lowestPrice;
    }

    public void setLowestPrice(double lowestPrice) {
        this.lowestPrice = lowestPrice;
    }

    public double getHighestPrice() {
        return highestPrice;
    }

    public void setHighestPrice(double highestPrice) {
        this.highestPrice = highestPrice;
    }

    public void addComment(double newRating) {
        rating = CommentUtil.addComment(rating, commentCount, newRating);
        commentCount++;
    }

    public void updateComment(double oldRating, double newRating) {
        rating = CommentUtil.updateComment(rating, commentCount, oldRating, newRating);
    }

    public void deleteComment(double ratingToRemove) {
        rating = CommentUtil.deleteComment(rating, commentCount, ratingToRemove);
        commentCount--;
    }
}
