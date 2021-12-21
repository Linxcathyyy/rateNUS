package com.rateNUS.backend.user;

import com.rateNUS.backend.security.ApplicationUserRole;
import com.rateNUS.backend.security.PasswordConfig;

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
import javax.persistence.UniqueConstraint;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
public class User {
    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    @Column(name = "id", updatable = false)
    private long id;

    @Column(name = "username", nullable = false, columnDefinition = "TEXT")
    private String username;

    @Column(name = "email", nullable = false, columnDefinition = "TEXT")
    private String email;

    @Column(name = "password", nullable = false, columnDefinition = "TEXT")
    private String password;

    @Column(name = "enabled")
    private boolean enabled;

    @ElementCollection
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "roles", nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<ApplicationUserRole> roles = new HashSet<>();

    public User() {}

    // For dummy data
    public User(String username, String email, String password, boolean enabled) {
        this.username = username;
        this.email = email;
        this.password = new PasswordConfig().passwordEncoder().encode(password);
        this.roles.add(ApplicationUserRole.USER);
        this.enabled = enabled;
    }

    // For UserRepository
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = new PasswordConfig().passwordEncoder().encode(password);
        this.roles.add(ApplicationUserRole.USER);
        this.enabled = false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<ApplicationUserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<ApplicationUserRole> roles) {
        this.roles = roles;
    }
}
