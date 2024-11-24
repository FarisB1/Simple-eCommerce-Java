package com.ahmedfaris.demo.Repositories;

import com.ahmedfaris.demo.Models.AppUser;
import com.ahmedfaris.demo.Models.Order;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(AppUser customerId);

    List<Order> findByUserId(Integer id);

    @Transactional
    void deleteByUserId(Integer userId);
}
