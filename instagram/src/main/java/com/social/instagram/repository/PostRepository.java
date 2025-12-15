package com.social.instagram.repository;




import com.social.instagram.model.post;
import com.social.instagram.model.user;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<post, Long> {

    // For feed: posts by followed users
    List<post> findByUserInOrderByCreatedAtDesc(List<user> users);

    // For user profile
    List<post> findByUserOrderByCreatedAtDesc(user user);
}
