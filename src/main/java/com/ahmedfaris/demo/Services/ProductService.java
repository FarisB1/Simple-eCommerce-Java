package com.ahmedfaris.demo.Services;

import com.ahmedfaris.demo.Models.Product;
import com.ahmedfaris.demo.Repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        List<Product> products = productRepository.findAll();
        logger.info("Retrieved products: {}", products);
        return products;
    }

    public Product getProductById(int id) {
        return productRepository.findById(id).get(); // MOZDA ERROR BUDE
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(int id) {
        productRepository.deleteById(id); //MOZDA I OVDJE ERROR
    }

}

