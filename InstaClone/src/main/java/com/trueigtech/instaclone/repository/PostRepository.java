package com.trueigtech.instaclone.repository;

import com.trueigtech.instaclone.model.Post;
import com.trueigtech.instaclone.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByUserInOrderByCreatedAtDesc(List<User> users);

    public List<Post> findByUserIn(Set<User> following);

    
}
