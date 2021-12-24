package com.rateNUS.backend.hostel;

import com.rateNUS.backend.image.Image;
import com.rateNUS.backend.util.CommentUtil;
import com.rateNUS.backend.util.Facility;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table
public class Hostel {
    @Id
    @SequenceGenerator(name = "hostel_sequence", sequenceName = "hostel_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hostel_sequence")
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

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @ElementCollection
    @JoinTable(name = "hostel_image", joinColumns = @JoinColumn(name = "hostel_id"))
    @Column(name = "image", nullable = false)
    private List<Image> images;

    @ElementCollection(targetClass = Facility.class)
    @JoinTable(name = "facilities", joinColumns = @JoinColumn(name = "hostel_id"))
    @Column(name = "facility", nullable = false)
    @Enumerated(EnumType.STRING)
    private List<Facility> facilities;

    public Hostel() {}

    // For dummy data
    public Hostel(String name, String location, String description, List<Image> images, List<Facility> facilities) {
        this.name = name;
        this.rating = -1;
        this.location = location;
        this.description = description;
        this.images = images;
        this.facilities = facilities;
    }

    // For HostelRepository
    public Hostel(long id, String name, double rating, int commentCount, String location, String description,
                  List<Image> images, List<Facility> facilities) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.commentCount = commentCount;
        this.location = location;
        this.description = description;
        this.images = images;
        this.facilities = facilities;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
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

    public String getDescription() {
        return description;
    }

    public List<Image> getImages() {
        return images;
    }

    public List<Facility> getFacilities() {
        return facilities;
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
