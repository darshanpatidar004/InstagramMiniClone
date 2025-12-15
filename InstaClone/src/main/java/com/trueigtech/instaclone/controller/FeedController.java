package com.trueigtech.instaclone.controller;

import com.trueigtech.instaclone.model.Post;
import com.trueigtech.instaclone.model.User;
import com.trueigtech.instaclone.repository.PostRepository;
import com.trueigtech.instaclone.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//  create feed controller
@RestController
@RequestMapping("/feed")
public class FeedController {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public FeedController(UserRepository userRepository,PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @GetMapping
    public List<Post> getFeed(HttpServletRequest request) {

        String email = (String) request.getAttribute("email");
        User user = userRepository.findByEmail(email).orElseThrow();

        return postRepository.findByUserInOrderByCreatedAtDesc(user.getFollowing().stream().toList());
    }
}
