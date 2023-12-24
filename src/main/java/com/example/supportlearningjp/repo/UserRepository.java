package com.example.supportlearningjp.service;

import com.example.supportlearningjp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByEmail(String email);

    Optional<User> findByUserNameOrEmail(String username,String email);
    Optional<User> findByUserName(String username);
    Boolean existsByEmail(String email);
    Boolean existsByUsername(String username);
}
