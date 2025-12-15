package com.social.instagram.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.social.instagram.model.comment;
import com.social.instagram.service.CommentService;

// this is comment controller which handle comment related requests
@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{postId}")
    public ResponseEntity<comment> addComment(
            @PathVariable Long postId,
            @RequestBody String content) {

        return ResponseEntity.ok(commentService.addComment(postId, content));
    }
}