package com.trueigtech.instaclone.service;

import com.trueigtech.instaclone.model.Comment;
import com.trueigtech.instaclone.model.Post;
import com.trueigtech.instaclone.model.User;
import com.trueigtech.instaclone.repository.CommentRepository;

import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment addComment(Comment comment, User user, Post post) {

        if (comment.getText() == null || comment.getText().trim().isEmpty()) {
            throw new RuntimeException("Comment cannot be empty");
        }

        comment.setUser(user);
        comment.setPost(post);

        return commentRepository.save(comment);
    }
}
