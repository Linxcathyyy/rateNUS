package com.rateNUS.backend.security.reset_password_token;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ResetPasswordTokenRepository extends JpaRepository<ResetPasswordToken, Long> {
    ResetPasswordToken findByToken(String token);

    @Transactional
    long deleteByToken(String token);
}
