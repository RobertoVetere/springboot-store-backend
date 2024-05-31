package com.hlidevs.store_products.services;

import com.hlidevs.store_products.entities.Product;
import com.hlidevs.store_products.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();

        return products = productRepository.findAll();
    }
}
