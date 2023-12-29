package com.example.doanweblaptop.dao;

import com.example.doanweblaptop.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository  extends JpaRepository<User,Long> {
    User findByUsername(String username);

    List<User> findByNameContainingOrUsernameContaining(String name, String username);
}
