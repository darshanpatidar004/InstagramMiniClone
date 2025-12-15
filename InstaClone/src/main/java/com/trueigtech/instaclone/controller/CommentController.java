package com.trueigtech.instaclone.controller;

import com.trueigtech.instaclone.model.User;
import com.trueigtech.instaclone.model.Comment;
import com.trueigtech.instaclone.model.Post;
import com.trueigtech.instaclone.repository.CommentRepository;
import com.trueigtech.instaclone.repository.PostRepository;
import com.trueigtech.instaclone.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public CommentController(CommentRepository commentRepository,
                             PostRepository postRepository,
                             UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/{postId}")
    public Comment addComment(@PathVariable Long postId,
                              @RequestBody Comment comment,
                              HttpServletRequest request) {

        String email = (String) request.getAttribute("email");
        Object user = userRepository.findByEmail(email).orElseThrow();
        Post post = postRepository.findById(postId).orElseThrow();

        if (comment.getText() == null || comment.getText().trim().isEmpty()) {
            throw new RuntimeException("Comment text cannot be empty");
        }

        comment.setUser((User) user);
        comment.setPost(post);

        return commentRepository.save(comment);
    }
}
