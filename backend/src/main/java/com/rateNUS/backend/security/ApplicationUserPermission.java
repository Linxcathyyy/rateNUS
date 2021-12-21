package com.rateNUS.backend.security;

public enum ApplicationUserPermission {
    HOSTEL_READ("hostel:read"),
    HOSTEL_WRITE("hostel:write"),
    COMMENT_READ("comment:read"),
    COMMENT_WRITE("comment:write"),

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
