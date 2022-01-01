package com.rateNUS.backend.security.reset_password_token;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface ResetPasswordTokenRepository extends JpaRepository<ResetPasswordToken, Long> {
    ResetPasswordToken findByToken(String token);

    ResetPasswordToken findByEmail(String email);

    Boolean existsByEmail(String email);

    @Transactional
    long deleteByToken(String token);
}
