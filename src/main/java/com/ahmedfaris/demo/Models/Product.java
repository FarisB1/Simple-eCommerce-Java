package com.ahmedfaris.demo.Models;

import jakarta.persistence.*;
import jakarta.persistence.criteria.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "product")
public class Product {
    @Column(name="id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name")
    @NotNull()
    @Size(min = 2, max = 100, message = "Naziv mora imati između 2 i 100 znakova.")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="price")
    @NotNull()
    @DecimalMin(value = "0.01", message = "Cijena mora biti veća od 0.")
    private double price;

    @Column(name="stock")
    @NotNull
    @Min(value = 0, message = "Količina ne može biti manja od 0.")
    private Integer stock;

    @Column(name="image")
    private String image;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @Size(min = 2, max = 100, message = "Naziv mora imati između 2 i 100 znakova.") String getName() {
        return name;
    }

    public void setName(@Size(min = 2, max = 100, message = "Naziv mora imati između 2 i 100 znakova.") String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @DecimalMin(value = "0.01", message = "Cijena mora biti veća od 0.")
    public double getPrice() {
        return price;
    }

    public void setPrice(@DecimalMin(value = "0.01", message = "Cijena mora biti veća od 0.") double price) {
        this.price = price;
    }

    @Min(value = 0, message = "Količina ne može biti manja od 0.")
    public Integer getStock() {
        return stock;
    }

    public void setStock(@Min(value = 0, message = "Količina ne može biti manja od 0.") int stock) {
        this.stock = stock;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String imageUrl) {
        this.image = imageUrl;
    }


}
