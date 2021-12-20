package com.rateNUS.backend.security;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import org.springframework.beans.factory.annotation.Value;

import com.rateNUS.backend.user.User;

/**
 * This class represents token generated during registration.
 */
@Entity
public class VerificationTokenUtils {
    @Value("${ratenus.app.verificationToken.tokenExpirationMin}")
    private int tokenExpirationMin;

    @Id
    @SequenceGenerator(name = "token_sequence", sequenceName = "token_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "token_sequence")
    private long id;

    private String token;

    // Ensures a one-to-one relationship between user and token.
    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    private Date expiryDate;

    private Date calculateExpiryDate(int expiryTimeInMinutes) {
        Calendar calender = Calendar.getInstance();
        calender.setTime(new Timestamp(calender.getTime().getTime()));
        calender.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(calender.getTime().getTime());
    }
}
