package com.trueigtech.instaclone.service;

import com.trueigtech.instaclone.model.User;
import com.trueigtech.instaclone.repository.UserRepository;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void followUser(User currentUser, User targetUser) {

        if (currentUser.getId().equals(targetUser.getId())) {
            throw new RuntimeException("Cannot follow yourself");
        }

        if (!currentUser.getFollowing().contains(targetUser)) {
            currentUser.getFollowing().add(targetUser);
            targetUser.getFollowers().add(currentUser);

            userRepository.save(currentUser);
            userRepository.save(targetUser);
        }
    }

    public void unfollowUser(User currentUser, User targetUser) {

        currentUser.getFollowing().remove(targetUser);
        targetUser.getFollowers().remove(currentUser);

        userRepository.save(currentUser);
        userRepository.save(targetUser);
    }
}
