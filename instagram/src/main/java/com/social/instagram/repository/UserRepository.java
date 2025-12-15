package com.social.instagram.repository;




import com.social.instagram.model.user;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<user, Long> {

    Optional<user> findByUsername(String username);

    boolean existsByUsername(String username);
}

