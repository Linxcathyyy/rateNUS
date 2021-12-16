package com.rateNUS.backend.user;

import com.rateNUS.backend.exception.InvalidInputException;
import com.rateNUS.backend.security.ApplicationUserRole;
import com.rateNUS.backend.security.PasswordConfig;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
public class User {
    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    @Column(name = "id", updatable = false)
    private long id;

    @Column(name = "username", nullable = false, columnDefinition = "TEXT")
    private String username;

    @Column(name = "email", nullable = false, columnDefinition = "TEXT")
    private String email;

    @Column(name = "password", nullable = false, columnDefinition = "TEXT")
    private String password;

    @ElementCollection
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "roles", nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<ApplicationUserRole> roles = new HashSet<>();

    private static final int USERNAME_MIN_LEN = 3;
    private static final int USERNAME_MAX_LEN = 20;
    private static final int PASSWORD_LENGTH = 8;
    private static final String SPECIAL_CHARACTERS = "`~!@#$%^&*()_+-=[]{}|;:'\",.<>/?";

    public User() {}

    public User(String username, String email, String password) throws InvalidInputException {
        if (isValidUsername(username)) {
            this.username = username;
        }
        if (isValidEmail(email)) {
            this.email = email;
        }
        if (isValidPassword(password)) {
            this.password = new PasswordConfig().passwordEncoder().encode(password);
        }
        this.roles.add(ApplicationUserRole.USER);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
