package com.rateNUS.backend.comment;

import com.rateNUS.backend.util.Type;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Provides access to database required by {@code CommentService}.
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByTargetIdAndType(long targetId, Type type, Sort sort);

    List<Comment> findByUserId(long userId, Sort sort);
}
