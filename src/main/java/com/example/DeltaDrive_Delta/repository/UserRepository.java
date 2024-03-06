package com.example.DeltaDrive_Delta.repository;

import com.example.DeltaDrive_Delta.entities.Role;
import com.example.DeltaDrive_Delta.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);
    User findByRole(Role email);
}
