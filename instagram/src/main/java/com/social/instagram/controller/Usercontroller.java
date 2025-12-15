package com.social.instagram.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.social.instagram.service.UserService;


// this is user controller which handle user related requests
@RestController
@RequestMapping("/api/users")
public class Usercontroller {

    private final UserService userService;

    public Usercontroller(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/{userId}/follow")
    public ResponseEntity<String> followUser(@PathVariable Long userId) {
        userService.followUser(userId);
        return ResponseEntity.ok("User followed successfully");
    }

    @PostMapping("/{userId}/unfollow")
    public ResponseEntity<String> unfollowUser(@PathVariable Long userId) {
        userService.unfollowUser(userId);
        return ResponseEntity.ok("User unfollowed successfully");
    }
}
