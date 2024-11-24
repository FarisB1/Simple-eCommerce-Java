package com.ahmedfaris.demo.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username", unique = true, nullable = false)
    @NotNull
    @Size(min = 3, max = 50)
    private String username;

    @Column(name = "password", nullable = false)
    @NotNull
    @Size(min = 6)
    private String password;

    @Column(name = "email", unique = true)
    @Email
    @NotNull
    private String email;


    @ManyToOne
    @JoinColumn(name = "role_id") // Foreign key to the Role table
    private Role role; // Example: "ADMIN", "USER"



    @Column(nullable = false)
    private boolean enabled = true; // Default to true




    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @Size(min = 3, max = 50) String getUsername() {
        return username;
    }

    public void setUsername(@Size(min = 3, max = 50) String username) {
        this.username = username;
    }


    public @Size(min = 6) String getPassword() {
        return password;
    }

    public void setPassword(@Size(min = 6) String password) {
        this.password = password;
    }

    public @Email String getEmail() {
        return email;
    }

    public void setEmail(@Email String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }



}
