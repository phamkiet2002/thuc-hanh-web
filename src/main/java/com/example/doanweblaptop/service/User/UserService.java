package com.example.doanweblaptop.service.User;

import com.example.doanweblaptop.dto.UserDTO;
import com.example.doanweblaptop.entity.Role;
import com.example.doanweblaptop.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(Long id);
    User save(User user);
    void deleteById(Long id);
    User findByUsername(String username);
    Role findByName(String rolename);
    User save(UserDTO userDTO);
    List<User> findByNameContainingOrUsernameContaining(String keyUser);
    Page<User> findByNameContainingOrUsernameContaining(String keyUser, Integer page);
    Page<User> findAll(Integer page);

}
