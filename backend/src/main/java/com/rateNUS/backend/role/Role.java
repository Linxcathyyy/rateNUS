package com.rateNUS.backend.role;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.rateNUS.backend.security.ApplicationUserRole;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private ApplicationUserRole name;

    public Role() {
    }

    public Role(ApplicationUserRole name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ApplicationUserRole getName() {
        return name;
    }

    public void setName(ApplicationUserRole name) {
        this.name = name;
    }
}
