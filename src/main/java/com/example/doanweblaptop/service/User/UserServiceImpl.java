package com.example.doanweblaptop.service.User;

import com.example.doanweblaptop.dao.RoleRepository;
import com.example.doanweblaptop.dao.UserRepository;
import com.example.doanweblaptop.dto.UserDTO;
import com.example.doanweblaptop.entity.Role;
import com.example.doanweblaptop.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        Optional<User> optional = userRepository.findById(id);
        User user = null;
        if (optional.isPresent())
            user = optional.get();
        else
                throw new RuntimeException("Khong tim thay " + id + "nay");
        return user;
    }

    @Override
    public User save(User user) {
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findByName("ROLE_KHACHHANG"));
        user.setRoles(roles);
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Role findByName(String rolename) {
        return roleRepository.findByName(rolename);
    }

    @Override
    public User save(UserDTO userDTO) {
        User user =new User();
        user.setName(userDTO.getName());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());

        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findByName("ROLE_KHACHHANG"));
        //roles.add(roleRepository.findByName("ROLE_ADMIN"));
        user.setRoles(roles);

        return userRepository.save(user);
    }

    @Override
    public List<User> findByNameContainingOrUsernameContaining(String keyUser) {
        return userRepository.findByNameContainingOrUsernameContaining(keyUser, keyUser);
    }

    @Override
    public Page<User> findByNameContainingOrUsernameContaining(String keyUser, Integer page) {
        List<User> users = userRepository.findByNameContainingOrUsernameContaining(keyUser, keyUser);
        Pageable pageable = PageRequest.of(page-1, 9);
        Integer start =(int) pageable.getOffset();
        Integer end = (int) ((pageable.getPageSize() + pageable.getOffset()) > users.size()? users.size():(pageable.getPageSize() + pageable.getOffset()));
        return new PageImpl<User>(users, pageable, userRepository.findByNameContainingOrUsernameContaining(keyUser, keyUser).size());
    }

    @Override
    public Page<User> findAll(Integer page) {
        Pageable pageable= PageRequest.of(page-1,9);
        return userRepository.findAll(pageable);
    }
}
