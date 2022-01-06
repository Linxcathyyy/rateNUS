package com.rateNUS.backend.user;

import com.rateNUS.backend.comment.Comment;
import com.rateNUS.backend.comment.CommentService;
import com.rateNUS.backend.exception.TypeNotFoundException;
import com.rateNUS.backend.util.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final CommentService commentService;

    @Autowired
    public UserService(UserRepository userRepository, CommentService commentService) {
        this.userRepository = userRepository;
        this.commentService = commentService;
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

    public boolean userHasComment(String username, long commentId) {
        Optional<Comment> comment = commentService.getComment(commentId);
        long commentUserId = comment.orElseThrow().getUserId();
        User user = this.findByUsername(username);
        assert (user != null);
        return commentUserId == user.getId();
    }

    public Map<Long, String> getUsernames(List<Long> ids) {
        Map<Long, String> map = new HashMap<>();
        ids.forEach(id -> userRepository.findById(id).ifPresentOrElse(
                user -> map.put(id, user.getUsername()),
                () -> map.put(id, "Error 404 User Not Found")
        ));

        return map;
    }
}
