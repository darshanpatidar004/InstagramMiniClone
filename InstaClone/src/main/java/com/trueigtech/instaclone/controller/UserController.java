package com.trueigtech.instaclone.controller;

import com.trueigtech.instaclone.model.User;
import com.trueigtech.instaclone.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Follow a user
    @PostMapping("/{id}/follow")
    public String followUser(@PathVariable Long id,HttpServletRequest request) {

        String email = (String) request.getAttribute("email");
        User currentUser = userRepository.findByEmail(email).orElseThrow();
        User targetUser = userRepository.findById(id).orElseThrow();

        if (currentUser.getId().equals(targetUser.getId())) {
            return "You cannot follow yourself";
        }

        if (!currentUser.getFollowing().contains(targetUser)) {
            currentUser.getFollowing().add(targetUser);
            targetUser.getFollowers().add(currentUser);

            userRepository.save(currentUser);
            userRepository.save(targetUser);
        }

        return "User followed";
    }

    // Unfollow a user
    @PostMapping("/{id}/unfollow")
    public String unfollowUser(@PathVariable Long id,HttpServletRequest request) {

        String email = (String) request.getAttribute("email");
        User currentUser = userRepository.findByEmail(email).orElseThrow();
        User targetUser = userRepository.findById(id).orElseThrow();

        currentUser.getFollowing().remove(targetUser);
        targetUser.getFollowers().remove(currentUser);

        userRepository.save(currentUser);
        userRepository.save(targetUser);

        return "User unfollowed";
    }

    // View profile
    @GetMapping("/{id}")
    public User viewProfile(@PathVariable Long id) {
        return userRepository.findById(id).orElseThrow();
    }
}
