package com.rateNUS.backend.auth;

import java.util.Set;

import com.rateNUS.backend.security.ApplicationUserRole;

public class SignupRequest {
    private String username;
    private String email;
    private String password;
    private Set<ApplicationUserRole> roles;

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

    public Set<ApplicationUserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<ApplicationUserRole> roles) {
        this.roles = roles;
    }
}
