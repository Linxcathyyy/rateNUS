package com.rateNUS.backend.security.verification_token;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

/**
 * This class represents token generated during registration.
 */
@Entity
@Table
public class VerificationToken {
    private int tokenExpirationMin = 60 * 24;

    @Id
    @SequenceGenerator(name = "token_sequence", sequenceName = "token_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "token_sequence")
    @Column(name = "id", updatable = false)
    private long id;

    @Column(name = "token", nullable = false, columnDefinition = "TEXT")
    private String token;

    @Column(name = "username", nullable = false, columnDefinition = "TEXT")
    private String username;

    @Column(name = "expiryDate", updatable = false)
    private Date expiryDate;

    private Date calculateExpiryDate(int expiryTimeInMinutes) {
        Calendar calender = Calendar.getInstance();
        calender.setTime(new Timestamp(calender.getTime().getTime()));
        calender.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(calender.getTime().getTime());
    }

    public VerificationToken() {
    }

    public VerificationToken(String token, String username) {
        this.token = token;
        this.username = username;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}
