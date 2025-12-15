package com.social.instagram.service;

import com.social.instagram.dto.PostRequest;
import com.social.instagram.model.like;
import com.social.instagram.model.post;
import com.social.instagram.model.user;
import com.social.instagram.repository.LikeRepository;
import com.social.instagram.repository.PostRepository;
import com.social.instagram.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;

    public PostService(PostRepository postRepository,
                       UserRepository userRepository,
                       LikeRepository likeRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.likeRepository = likeRepository;
    }

    public post createPost(PostRequest request) {

        user user = getLoggedInUser();

        post post = new post();
        post.setImageUrl(request.getImageUrl());
        post.setCaption(request.getCaption());
        post.setUser(user);

        return postRepository.save(post);
    }

    public void likePost(Long postId) {

        user user = getLoggedInUser();
        post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        if (likeRepository.findByUserAndPost(user, post).isPresent()) {
            return; // already liked
        }

        like like = new like();
        like.setUser(user);
        like.setPost(post);

        likeRepository.save(like);
    }

    public void unlikePost(Long postId) {

        user user = getLoggedInUser();
        post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        likeRepository.findByUserAndPost(user, post)
                .ifPresent(likeRepository::delete);
    }

    public List<post> getFeed() {

        user user = getLoggedInUser();

        return postRepository.findByUserInOrderByCreatedAtDesc(
                user.getFollowing().stream().toList()
        );
    }

    private user getLoggedInUser() {
        String username = SecurityContextHolder.getContext()
                .getAuthentication().getName();

        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
