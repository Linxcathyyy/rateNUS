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

    public Hostel() {
    }

    public Hostel(String name, double rating, String location, String description) {
        this.name = name;
        this.rating = rating;
        this.location = location;
        this.description = description;
    }

    public Hostel(long id, String name, double rating, String location, String description) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.location = location;
        this.description = description;
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
                && Objects.equals(description, hostel.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, rating, location, description);
    }

    @Override
    public String toString() {
        return "Hostel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
