package com.example.toystore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.toystore.model.Product;
import com.example.toystore.repository.ProductRepo;

@Service
public class ProductService {
    @Autowired
    private final ProductRepo pr;

    public ProductService(ProductRepo pr) {
        this.pr = pr;
    }

    public Product create(Product obj) {
        return pr.save(obj);
    }

    public List<Product> getAll() {
        return pr.findAll();
    }

    public Product getById(int id) {
        return pr.findById(id).orElse(null);
    }

    public Product update(int productId, Product product) {
        Product model = pr.findById(productId).orElse(null);
        if (model != null) {
            model.setProductId(product.getProductId());
            model.setProductName(product.getProductName());
            pr.save(model);
            return model;
        } else {
            return product;
        }
    }

    public String delete(int productId) {
        pr.deleteById(productId);
        return "Deleted Successfully";

    }
}