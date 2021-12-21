package com.rateNUS.backend.auth.registrationevent;

import org.springframework.context.ApplicationEvent;

import com.rateNUS.backend.user.User;

public class RegistrationCompleteEvent extends ApplicationEvent {
    private User user;

    public RegistrationCompleteEvent(User user) {
        super(user);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
