package com.example.toystore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.toystore.model.Product;
import com.example.toystore.service.ProductService;

@RestController
public class ProductController {
    @Autowired
    private final ProductService ps;

    public ProductController(ProductService ps) {
        this.ps = ps;
    }

    @PostMapping("/api/Product/add")
    public ResponseEntity<Product> create(@RequestBody Product obj) {
        return new ResponseEntity<>(ps.create(obj), HttpStatus.CREATED);
    }

    @GetMapping("/api/Product/get")
    public ResponseEntity<List<Product>> getAll() {
        return new ResponseEntity<>(ps.getAll(), HttpStatus.OK);
    }

    @GetMapping("/api/Product/get/{productId}")
    public ResponseEntity<Product> getById(@PathVariable int productId) {
        Product obj = ps.getById(productId);
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @PutMapping("/api/Product/put/{productId}")
    public Product putSignUp(@PathVariable int productId, @RequestBody Product product) {
        return ps.update(productId, product);
    }

    @DeleteMapping("/api/Product/delete/{productId}")
    public String delete(@PathVariable int productId) {
        return ps.delete(productId);
    }

}