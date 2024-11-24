package com.ahmedfaris.demo.Services;

import com.ahmedfaris.demo.Models.AppUser;
import com.ahmedfaris.demo.Models.Order;
import com.ahmedfaris.demo.Repositories.OrderItemRepository;
import com.ahmedfaris.demo.Repositories.OrderRepository;
import com.ahmedfaris.demo.Repositories.RoleRepository;
import com.ahmedfaris.demo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 1. Find all users
    public List<AppUser> findAll() {
        return userRepository.findAll();
    }

    // 2. Find user by ID
    public Optional<AppUser> findById(Integer id) {
        return userRepository.findById(id);
    }

    // 3. Create a new user with password encryption
    public AppUser save(AppUser appUser) {
        appUser.setPassword(appUser.getPassword()); // Encrypt password
        return userRepository.save(appUser);
    }

    // 4. Update an existing user
    public AppUser updateUsers(Integer id, AppUser appUserDetails) {
        Optional<AppUser> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            AppUser updatedAppUser = existingUser.get();
            updatedAppUser.setUsername(appUserDetails.getUsername());
            updatedAppUser.setEmail(appUserDetails.getEmail());

            // Update the password only if provided
            if (appUserDetails.getPassword() != null && !appUserDetails.getPassword().isEmpty()) {
                updatedAppUser.setPassword(appUserDetails.getPassword());
            }

            updatedAppUser.setRole(appUserDetails.getRole());
            return userRepository.save(updatedAppUser);
        }
        return null;
    }

    // 5. Delete a user by ID
    public boolean delete(Integer id) {
        Optional<AppUser> user = userRepository.findById(id);
        if (user.isPresent()) {
            try {
                // Delete related orders and their items
                List<Order> orders = orderRepository.findByUserId(id);
                for (Order order : orders) {
                    orderItemRepository.deleteByOrderId(order.getId());
                }
                orderRepository.deleteByUserId(id);

                // Finally, delete the user
                userRepository.delete(user.get());
                return true;
            } catch (DataIntegrityViolationException e) {
                throw new IllegalStateException("Cannot delete user. User is associated with existing records.", e);
            }
        }
        return false; // User not found
    }



    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<AppUser> appUser = userRepository.findByEmail(email);

        if (appUser.isPresent()) {
            var springUser = User.withUsername(appUser.get().getEmail())
                    .password(appUser.get().getPassword())
                    .roles(appUser.get().getRole().getName())
                    .build();

            return springUser;
        }
        return null;
    }

    public String getCurrentUsername() {
        Authentication authenticationToken = SecurityContextHolder.getContext().getAuthentication();
        if (authenticationToken != null && authenticationToken.isAuthenticated()) {
            Object principal = authenticationToken.getPrincipal();
            if (principal instanceof String) {
                return (String) principal;
            } else if (principal instanceof User) {
                return ((User) principal).getUsername();
            }
        }
        return null;
    }

}
