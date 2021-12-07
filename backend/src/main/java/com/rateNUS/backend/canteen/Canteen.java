package com.rateNUS.backend.canteen;

import java.util.Objects;

public class Canteen {
    private long id;
    private String name;
    private String location;
    private String description;

    public Canteen(long id, String name, String location, String description) {
        this.id = id;
        this.name = name;
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

        if (!(obj instanceof Canteen)) {
            return false;
        }

        Canteen canteen = (Canteen) obj;
        return id == canteen.id
                && Objects.equals(name, canteen.name)
                && Objects.equals(location, canteen.location)
                && Objects.equals(description, canteen.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, location, description);
    }

    @Override
    public String toString() {
        return "Canteen{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
