package com.rateNUS.backend.comment;

import com.rateNUS.backend.util.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Provides access to database required by {@code CommentService}.
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findByTargetIdAndType(long targetId, Type type, Pageable pageable);

    List<Comment> findByUserId(long userId, Sort sort);

    List<Comment> findAllByOrderByIdAsc();
}
