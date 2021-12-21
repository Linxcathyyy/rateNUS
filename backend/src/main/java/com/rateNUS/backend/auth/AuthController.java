package com.rateNUS.backend.auth;

import com.rateNUS.backend.auth.registration_event.RegistrationCompleteEvent;
import com.rateNUS.backend.auth.reset_password_event.ResetPasswordEvent;
import com.rateNUS.backend.security.ApplicationUserRole;
import com.rateNUS.backend.security.PasswordConfig;
import com.rateNUS.backend.security.UserDetailsImpl;
import com.rateNUS.backend.security.jwt.JwtUtils;
import com.rateNUS.backend.security.reset_password_token.ResetPasswordToken;
import com.rateNUS.backend.security.reset_password_token.ResetPasswordTokenRepository;
import com.rateNUS.backend.security.verification_token.VerificationToken;
import com.rateNUS.backend.security.verification_token.VerificationTokenRepository;
import com.rateNUS.backend.user.User;
import com.rateNUS.backend.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Serves as the API for authentication.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class AuthController {
    @Autowired
    AuthenticationManager authenticateManager;

    @Autowired
    UserService userService;

    @Autowired
    PasswordConfig passwordConfig;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    ApplicationEventPublisher eventPublisher;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private ResetPasswordTokenRepository resetPasswordTokenRepository;

    @PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        User user = userService.findByUsername(loginRequest.getUsername());
        if (user == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("User doesn't exist."));
        }

        if (!userService.isEnabled(user)) {
            return ResponseEntity.badRequest().body(
                    new MessageResponse("User's email address hasn't been confirmed."));
        }

        Authentication authentication = authenticateManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + jwt);

        return new ResponseEntity<>(
                new JwtResponse(userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles),
                headers,
                HttpStatus.OK);
    }

    @PostMapping(path = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signupRequest) {
        String username = signupRequest.getUsername();
        String email = signupRequest.getEmail();
        String password = passwordConfig.passwordEncoder().encode(signupRequest.getPassword());

        if (userService.existsByEmail(email)) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email has been registered."));
        }
        if (userService.existsByUsername(username)) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username has been registered."));
        }

        User user = new User(username, email, password);
        // email verification
        eventPublisher.publishEvent(new RegistrationCompleteEvent(user));
        // user account saved to database but cannot be used until email is verified.
        userService.addUser(user);

        return ResponseEntity.ok(
                new MessageResponse("User registration is in progress, waiting for email confirmation."));
    }

    @GetMapping(path = "/registrationConfirm")
    public ResponseEntity<?> confirmRegistration(@RequestParam("token") String token) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
        if (verificationToken == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Verification token is invalid."));
        }

        Calendar calendar = Calendar.getInstance();
        if (verificationToken.getExpiryDate().getTime() - calendar.getTime().getTime() <= 0) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Verification token has expired."));
        }

        // save user
        User user = userService.findByUsername(verificationToken.getUsername());
        userService.enableUser(user);
        userService.addUser(user);

        // authenticate user
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null,
                AuthorityUtils.createAuthorityList(ApplicationUserRole.USER.name()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        List<String> roles = user.getRoles().stream()
                .map(item -> item.name())
                .collect(Collectors.toList());

        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + jwt);

        // delete token from database
        verificationTokenRepository.deleteByToken(token);

        return new ResponseEntity<>(
                new JwtResponse(user.getId(), user.getUsername(), user.getEmail(), roles),
                headers,
                HttpStatus.OK);
    }

    @PostMapping(path = "/forgetPassword")
    public ResponseEntity<?> forgetPassword(@RequestBody ForgetPasswordRequest forgetPasswordRequest) {
        String email = forgetPasswordRequest.getEmail();
        User user = userService.findByEmail(email);
        if (user == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: user is not registered."));
        }

        // verify user's identity through email
        eventPublisher.publishEvent(new ResetPasswordEvent(email));

        return ResponseEntity.ok(new MessageResponse(
                "Reset password in progress, waiting for user to verify identity through email."));
    }

    @PostMapping(path = "/resetPassword")
    public ResponseEntity<?> resetPassword(@RequestParam("token") String token,
                                           @RequestBody ResetPasswordRequest resetPasswordRequest) {
        ResetPasswordToken resetPasswordToken = resetPasswordTokenRepository.findByToken(token);
        if (resetPasswordToken == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Token is invalid."));
        }

        Calendar calendar = Calendar.getInstance();
        if (resetPasswordToken.getExpiryDate().getTime() - calendar.getTime().getTime() <= 0) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Token has expired."));
        }

        // reset password
        String email = resetPasswordToken.getEmail();
        String password = passwordConfig.passwordEncoder().encode(resetPasswordRequest.getPassword());
        User user = userService.findByEmail(email);
        userService.updateUserPassword(user.getId(), password);

        // delete token from database
        resetPasswordTokenRepository.deleteByToken(token);

        return ResponseEntity.ok(new MessageResponse("Reset password successfully."));
    }
}
