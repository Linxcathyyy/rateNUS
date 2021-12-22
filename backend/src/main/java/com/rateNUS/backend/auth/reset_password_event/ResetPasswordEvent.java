package com.rateNUS.backend.auth.reset_password_event;

import org.springframework.context.ApplicationEvent;

public class ResetPasswordEvent extends ApplicationEvent {
    private String email;

    public ResetPasswordEvent(String email) {
        super(email);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
