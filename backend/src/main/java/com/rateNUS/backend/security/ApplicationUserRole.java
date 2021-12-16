package com.rateNUS.backend.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.rateNUS.backend.security.ApplicationUserPermission.COMMENT_READ;
import static com.rateNUS.backend.security.ApplicationUserPermission.COMMENT_WRITE;
import static com.rateNUS.backend.security.ApplicationUserPermission.HOSTEL_READ;
import static com.rateNUS.backend.security.ApplicationUserPermission.HOSTEL_WRITE;

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

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
