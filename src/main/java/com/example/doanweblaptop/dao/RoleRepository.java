package com.example.doanweblaptop.dao;


import com.example.doanweblaptop.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository  extends JpaRepository<Role,Long> {
    Role findByName(String rolename);
}
