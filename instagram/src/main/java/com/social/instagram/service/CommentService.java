package com.social.instagram.service;

import com.social.instagram.model.comment;
import com.social.instagram.model.post;
import com.social.instagram.model.user;
import com.social.instagram.repository.CommentRepository;
import com.social.instagram.repository.PostRepository;
import com.social.instagram.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public CommentService(CommentRepository commentRepository,
                          PostRepository postRepository,
                          UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public comment addComment(Long postId, String content) {

        user user = getLoggedInUser();
        post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        comment comment = new comment();
        comment.setContent(content);
        comment.setUser(user);
        comment.setPost(post);

        return commentRepository.save(comment);
    }

    private user getLoggedInUser() {
        String username = SecurityContextHolder.getContext()
                .getAuthentication().getName();

        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}