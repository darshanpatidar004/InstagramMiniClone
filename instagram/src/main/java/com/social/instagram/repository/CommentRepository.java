package com.social.instagram.repository;




import com.social.instagram.model.comment;
import com.social.instagram.model.post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<comment, Long> {

    List<comment> findByPostOrderByCreatedAtAsc(post post);
}

