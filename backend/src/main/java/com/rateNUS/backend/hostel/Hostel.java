package com.rateNUS.backend.hostel;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;
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

    @Column(name = "commentCount")
    private int commentCount;

    @Column(name = "location", nullable = false, columnDefinition = "TEXT")
    private String location;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @ElementCollection
    @JoinTable(name = "images", joinColumns = @JoinColumn(name = "hostel_id"))
    @Column(name = "imageUrl", nullable = false)
    private List<String> imageUrl;

    @ElementCollection(targetClass = Facility.class)
    @JoinTable(name = "facilities", joinColumns = @JoinColumn(name = "hostel_id"))
    @Column(name = "facility", nullable = false)
    @Enumerated(EnumType.STRING)
    private List<Facility> facilities;

    public Hostel() {}

    // For dummy data
    public Hostel(String name, String location, String description, List<String> imageUrl, List<Facility> facilities) {
        this.name = name;
        this.rating = -1;
        this.location = location;
        this.description = description;
        this.imageUrl = imageUrl;
        this.facilities = facilities;
    }

    // For HostelRepository
    public Hostel(long id, String name, double rating, int commentCount, String location, String description,
                  List<String> imageUrl, List<Facility> facilities) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.commentCount = commentCount;
        this.location = location;
        this.description = description;
        this.imageUrl = imageUrl;
        this.facilities = facilities;
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

    public void incCommentCountByOne() {
        commentCount++;
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

    public List<Facility> getFacilities() {
        return facilities;
    }

    public void setFacilities(List<Facility> facilities) {
        this.facilities = facilities;
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
                && commentCount == hostel.commentCount
                && Objects.equals(name, hostel.name)
                && Objects.equals(location, hostel.location)
                && Objects.equals(description, hostel.description)
                && Objects.equals(imageUrl, hostel.imageUrl)
                && Objects.equals(facilities, hostel.facilities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, rating, commentCount, location, description, imageUrl, facilities);
    }

    @Override
    public String toString() {
        return "Hostel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                ", commentCount=" + commentCount +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl=" + imageUrl +
                ", facilities=" + facilities +
                '}';
    }
}
