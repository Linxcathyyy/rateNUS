package com.rateNUS.backend.security.reset_password_token;

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
 * This class represents token generated during password reset.
 */
@Entity
@Table
public class ResetPasswordToken {
    private int tokenExpirationMin = 60 * 24;

    @Id
    @SequenceGenerator(name = "password_token_sequence", sequenceName = "password_token_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "password_token_sequence")
    @Column(name = "id", updatable = false)
    private long id;

    @Column(name = "token", nullable = false, columnDefinition = "TEXT")
    private String token;

    @Column(name = "email", nullable = false, columnDefinition = "TEXT")
    private String email;

    @Column(name = "expiryDate", updatable = false)
    private Date expiryDate;

    private Date calculateExpiryDate(int expiryTimeInMinutes) {
        Calendar calender = Calendar.getInstance();
        calender.setTime(new Timestamp(calender.getTime().getTime()));
        calender.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(calender.getTime().getTime());
    }

    public ResetPasswordToken() {
    }

    public ResetPasswordToken(String token, String email) {
        this.token = token;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}
