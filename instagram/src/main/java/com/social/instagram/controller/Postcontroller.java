package com.social.instagram.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.social.instagram.dto.PostRequest;
import com.social.instagram.model.post;
import com.social.instagram.service.PostService;

// this is post controller which handle post related requests
@RestController
@RequestMapping("/api/posts")
public class Postcontroller {

    private final PostService postService;

    public Postcontroller(PostService postService) {
        this.postService = postService;
    }


    @PostMapping
    public ResponseEntity<post> createPost(@RequestBody PostRequest request) {
        return ResponseEntity.ok(postService.createPost(request));
    }


    @PostMapping("/{postId}/like")
    public ResponseEntity<String> likePost(@PathVariable Long postId) {
        postService.likePost(postId);
        return ResponseEntity.ok("Post liked");
    }

    @PostMapping("/{postId}/unlike")
    public ResponseEntity<String> unlikePost(@PathVariable Long postId) {
        postService.unlikePost(postId);
        return ResponseEntity.ok("Post unliked");
    }


    @GetMapping("/feed")
    public ResponseEntity<List<post>> getFeed() {
        return ResponseEntity.ok(postService.getFeed());
    }
}
