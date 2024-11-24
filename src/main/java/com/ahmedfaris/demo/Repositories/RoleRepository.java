package com.ahmedfaris.demo.Repositories;

import com.ahmedfaris.demo.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}