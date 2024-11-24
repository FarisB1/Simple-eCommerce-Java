package com.ahmedfaris.demo.Controllers;

import com.ahmedfaris.demo.Models.Product;
import com.ahmedfaris.demo.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Controller
@RequestMapping("/products")
public class ProductController {

    @Value("${upload.path}")
    private String uploadDir;

    @Autowired
    private ProductService productService;

    @GetMapping
    public String getProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "shop";
    }


    @GetMapping("/add")
    public String addProduct(Model model) {
        model.addAttribute("product", new Product());
        return "add_product";
    }

    @PostMapping("/add")
    public String addProduct(@RequestParam("name") String name,
                             @RequestParam("description") String description,
                             @RequestParam("price") double price,
                             @RequestParam("stock") int stock,
                             @RequestParam("image") MultipartFile image,
                             Model model) throws IOException {
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setStock(stock);

        if (!image.isEmpty()) {
            String imageName = image.getOriginalFilename();
            Path imagePath = Paths.get(uploadDir, imageName);

            Files.createDirectories(imagePath.getParent());

            image.transferTo(imagePath);

            product.setImage("/images/" + imageName);
        }

        productService.addProduct(product);
        model.addAttribute("product", product);

        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }
}
