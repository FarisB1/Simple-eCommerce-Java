package com.ahmedfaris.demo.Models;

import jakarta.validation.constraints.*;

public class RegisterDto {
    @NotEmpty
    private String name;
    @NotEmpty
    @Email
    private String email;

    @Size(min = 6, message = "Minimum password length is 6 characters")
    private String password;
    private String confirmPassword;

    public @NotEmpty String getName() {
        return name;
    }

    public void setName(@NotEmpty String name) {
        this.name = name;
    }

    public @NotEmpty String getEmail() {
        return email;
    }

    public void setEmail(@NotEmpty String email) {
        this.email = email;
    }

    public @Size(min = 6, message = "Minimum password length is 6 characters") String getPassword() {
        return password;
    }

    public void setPassword(@Size(min = 6, message = "Minimum password length is 6 characters") String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
