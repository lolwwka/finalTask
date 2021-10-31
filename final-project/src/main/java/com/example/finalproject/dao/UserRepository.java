package com.example.finalproject.dao;

import com.example.finalproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUsersByEmail(String email);

    User findByKeyCode(String keyCode);
}
