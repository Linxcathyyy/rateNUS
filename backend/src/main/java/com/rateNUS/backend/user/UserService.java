package com.rateNUS.backend.user;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rateNUS.backend.exception.TypeNotFoundException;
import com.rateNUS.backend.util.Type;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public void enableUser(User user) {
        user.setEnabled(true);
    }

    public boolean isEnabled(User user) {
        return user.isEnabled();
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public User updateUserPassword(long userId, String password) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new TypeNotFoundException(Type.user, userId));
        user.setPassword(password);
        return user;
    }
}
