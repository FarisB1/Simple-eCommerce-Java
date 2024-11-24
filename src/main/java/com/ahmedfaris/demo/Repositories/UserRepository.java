package com.ahmedfaris.demo.Repositories;
import com.ahmedfaris.demo.Models.AppUser;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser, Integer> {

     Optional<AppUser> findByEmail(String email);

     Optional<AppUser> findByUsername(String username);
}
