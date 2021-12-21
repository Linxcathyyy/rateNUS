package com.rateNUS.backend.auth.registration_event;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.rateNUS.backend.security.verification_token.VerificationToken;
import com.rateNUS.backend.security.verification_token.VerificationTokenRepository;
import com.rateNUS.backend.user.User;

@Component
public class RegistrationListener implements ApplicationListener<RegistrationCompleteEvent> {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        confirmRegistration(event);
    }

    private void confirmRegistration(RegistrationCompleteEvent event) {
        User user = event.getUser();
        String tokenString = UUID.randomUUID().toString();

        // create verification token
        VerificationToken verificationToken = new VerificationToken(tokenString, user);
        verificationTokenRepository.save(verificationToken);

        // send confirmation email
        String destination = user.getEmail();
        String subject = "rateNUS Registration Confirmation";
        String message = "Please verify your email to complete the registration with rateNUS!";
        String confirmationUrl = "/registrationConfirm?token=" + tokenString;

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(destination);
        email.setSubject(subject);
        email.setText(message + "\r\n" + "http://localhost:8080" + confirmationUrl);

        mailSender.send(email);
    }
}
