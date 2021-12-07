package com.rateNUS.backend.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Provides access to database required by {@code CommentService}.
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
