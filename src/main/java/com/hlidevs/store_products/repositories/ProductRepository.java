package com.hlidevs.store_products.repositories;

import com.hlidevs.store_products.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
