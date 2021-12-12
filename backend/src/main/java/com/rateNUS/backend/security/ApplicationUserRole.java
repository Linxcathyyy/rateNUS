package com.rateNUS.backend.security;

import static com.rateNUS.backend.security.ApplicationUserPermission.*;

import com.google.common.collect.Sets;

import java.util.Set;

public enum ApplicationUserRole {
    USER(Sets.newHashSet(HOSTEL_READ, COMMENT_READ, COMMENT_WRITE)),
    ADMIN(Sets.newHashSet(HOSTEL_READ, HOSTEL_WRITE, COMMENT_READ, COMMENT_WRITE));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }
}
