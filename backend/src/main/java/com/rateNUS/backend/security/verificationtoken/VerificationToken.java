package com.rateNUS.backend.security.verificationtoken;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Value;

import com.rateNUS.backend.user.User;

/**
 * This class represents token generated during registration.
 */
@Entity
@Table
public class VerificationToken {
    @Value("${ratenus.app.verificationToken.tokenExpirationMin}")
    private int tokenExpirationMin;

    @Id
    @SequenceGenerator(name = "token_sequence", sequenceName = "token_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "token_sequence")
    @Column(name = "id", updatable = false)
    private long id;

    @Column(name = "token", nullable = false, columnDefinition = "TEXT")
    private String token;

    // Ensures a one-to-one relationship between user and token.
    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    @Column(name = "expiryDate")
    private Date expiryDate;

    private Date calculateExpiryDate(int expiryTimeInMinutes) {
        Calendar calender = Calendar.getInstance();
        calender.setTime(new Timestamp(calender.getTime().getTime()));
        calender.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(calender.getTime().getTime());
    }

    public VerificationToken() {
    }

    public VerificationToken(String token, User user) {
        this.token = token;
        this.user = user;
        this.expiryDate = calculateExpiryDate(tokenExpirationMin);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}
