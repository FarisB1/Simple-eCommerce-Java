package com.ahmedfaris.demo.Repositories;

import com.ahmedfaris.demo.Models.AppUser;
import com.ahmedfaris.demo.Models.Cart;
import com.ahmedfaris.demo.Models.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartDao extends CrudRepository<Cart,Integer> {
    Optional<Cart> findByUserAndProduct(AppUser user, Product product);

    List<Cart> findByUser(AppUser user);
}
