package com.trueigtech.instaclone.repository;

import com.trueigtech.instaclone.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
