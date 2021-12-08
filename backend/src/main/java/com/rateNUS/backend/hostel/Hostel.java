package com.rateNUS.backend.hostel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Objects;

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

    @Column(name = "location", nullable = false, columnDefinition = "TEXT")
    private String location;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "commentCount")
    private int commentCount;

    public Hostel() {
    }

    public Hostel(String name, double rating, String location, String description, int commentCount) {
        this.name = name;
        this.rating = rating;
        this.location = location;
        this.description = description;
        this.commentCount = commentCount;
    }

    public Hostel(long id, String name, double rating, String location, String description, int commentCount) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.location = location;
        this.description = description;
        this.commentCount = commentCount;
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

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public void incCommentCountByOne() {
        commentCount++;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Hostel)) {
            return false;
        }

        Hostel hostel = (Hostel) obj;
        return id == hostel.id
                && Double.compare(hostel.rating, rating) == 0
                && Objects.equals(name, hostel.name)
                && Objects.equals(location, hostel.location)
                && Objects.equals(description, hostel.description)
                && commentCount == hostel.commentCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, rating, location, description, commentCount);
    }

    @Override
    public String toString() {
        return "Hostel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", commentCount='" + commentCount + '\'' +
                '}';
    }
}
