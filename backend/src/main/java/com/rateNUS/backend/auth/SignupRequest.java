package com.rateNUS.backend.auth;

import java.util.Set;

import com.rateNUS.backend.exception.InvalidInputException;
import com.rateNUS.backend.security.ApplicationUserRole;

public class SignupRequest {
    private String username;
    private String email;
    private String password;
    private Set<ApplicationUserRole> roles;

    private static final int USERNAME_MIN_LEN = 3;
    private static final int USERNAME_MAX_LEN = 20;
    private static final int PASSWORD_LENGTH = 8;
    private static final String SPECIAL_CHARACTERS = "`~!@#$%^&*()_+-=[]{}|;:'\",.<>/?";

    public SignupRequest() {
    }

    public SignupRequest(String username, String email, String password, Set<ApplicationUserRole> roles)
            throws InvalidInputException {
        if (isValidUsername(username)) {
            this.username = username;
        }
        if (isValidEmail(email)) {
            this.email = email;
        }
        if (isValidPassword(password)) {
            this.password = password;
        }
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) throws InvalidInputException {
        if (isValidUsername(username)) {
            this.username = username;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws InvalidInputException {
        if (isValidEmail(email)) {
            this.email = email;
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws InvalidInputException {
        if (isValidPassword(password)) {
            this.password = password;
        }
    }

    public Set<ApplicationUserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<ApplicationUserRole> roles) {
        this.roles = roles;
    }

    public boolean isValidUsername(String username) throws InvalidInputException {
        if (username == null || username.trim() == "" || username.length() == 0) {
            throw new InvalidInputException("Username cannot be empty.");
        }
        int length = username.length();
        if (length < USERNAME_MIN_LEN || length > USERNAME_MAX_LEN) {
            throw new InvalidInputException(
                    "The length of username must be in the range " + USERNAME_MIN_LEN + "-" + USERNAME_MAX_LEN);
        }
        return true;
    }

    public boolean isValidEmail(String email) {
        // todo: implement validity check later
        return true;
    }

    /**
     * Checks whether a password contains letters (both upper and lower cases), digits, special characters,
     * and is longer than {@code PASSWORD_LENGTH}.
     *
     * @param password the password to be checked
     * @return whether the password is valid
     * @throws InvalidInputException if the password is invalid
     */
    public boolean isValidPassword(String password) throws InvalidInputException {
        if (password == null || password.trim() == "" || password.length() == 0) {
            throw new InvalidInputException("Password cannot be empty");
        }
        int length = password.length();
        if (length < PASSWORD_LENGTH) {
            throw new InvalidInputException("The length of password cannot be shorter than " + PASSWORD_LENGTH);
        }

        int upperCaseLetterCount = 0;
        int lowerCaseLetterCount = 0;
        int digitCount = 0;
        int specialCharacterCount = 0;
        for (int i = 0; i < length; i++) {
            char character = password.charAt(i);
            if (isUpperCaseLetter(character)) {
                upperCaseLetterCount++;
            } else if (isLowerCaseLetter(character)) {
                lowerCaseLetterCount++;
            } else if (isDigit(character)) {
                digitCount++;
            } else if (isSpecialCharacter(character)) {
                specialCharacterCount++;
            }
        }
        if (upperCaseLetterCount < 1) {
            throw new InvalidInputException("Password must contain at least one upper case letter.");
        }
        if (lowerCaseLetterCount < 1) {
            throw new InvalidInputException("Password must contain at least one lower case letter.");
        }
        if (digitCount < 1) {
            throw new InvalidInputException("Password must contain at least one digit from 0-9.");
        }
        if (specialCharacterCount < 1) {
            throw new InvalidInputException("Password must contain at least one special character.");
        }

        return true;
    }

    private boolean isUpperCaseLetter(char character) {
        return character >= 'A' && character <= 'Z';
    }

    private boolean isLowerCaseLetter(char character) {
        return character >= 'a' && character <= 'z';
    }

    private boolean isDigit(char character) {
        return character >= '0' && character <= '9';
    }

    private boolean isSpecialCharacter(char character) {
        return SPECIAL_CHARACTERS.contains(String.valueOf(character));
    }
}
