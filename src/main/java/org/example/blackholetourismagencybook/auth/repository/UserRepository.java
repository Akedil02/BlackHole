package org.example.blackholetourismagencybook.auth.repository;

import org.example.blackholetourismagencybook.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
    User findByUsername(String username);
}
