package com.rateNUS.backend.auth.reset_password_event;

import java.util.UUID;

import com.rateNUS.backend.util.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.rateNUS.backend.security.reset_password_token.ResetPasswordToken;
import com.rateNUS.backend.security.reset_password_token.ResetPasswordTokenRepository;

@Component
public class ResetPasswordListener implements ApplicationListener<ResetPasswordEvent> {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private ResetPasswordTokenRepository resetPasswordTokenRepository;

    @Override
    public void onApplicationEvent(ResetPasswordEvent event) {
        resetPassword(event);
    }

    private void resetPassword(ResetPasswordEvent event) {
        String emailAddress = event.getEmail();
        String tokenString = UUID.randomUUID().toString();

        // create token
        ResetPasswordToken resetPasswordToken = new ResetPasswordToken(tokenString, emailAddress);
        resetPasswordTokenRepository.save(resetPasswordToken);

        // send email to check user's identity
        String destination = emailAddress;
        String subject = "rateNUS Reset Password";
        String message = "Reset your password here:";
        String confirmationUrl = "/resetPassword?token=" + tokenString;

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(destination);
        email.setSubject(subject);
        email.setText(message + "\r\n" + Config.frontendURL + "/#" + confirmationUrl);

        mailSender.send(email);
    }
}
