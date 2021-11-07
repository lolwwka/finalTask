package com.example.finalproject.repository;

import com.example.finalproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByEmailContaining(String email);
    User findByKeyCode(String keyCode);
    @Query(value = "select u from User u order by u.id asc OFFSET ?1 limit ?2", nativeQuery = true)
    List<User> getAll(int offset, int limit);
}
