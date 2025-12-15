package com.social.instagram.repository;


import com.social.instagram.model.like;
import com.social.instagram.model.post;
import com.social.instagram.model.user;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<like, Long> {

    Optional<like> findByUserAndPost(user user, post post);

    long countByPost(post post);
}

