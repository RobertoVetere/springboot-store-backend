package com.hlidevs.store_products;

import com.hlidevs.store_products.entities.Product;
import com.hlidevs.store_products.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class StoreProductsApplication implements CommandLineRunner {

	@Autowired
	ProductRepository productRepository;

	public static void main(String[] args) {
		SpringApplication.run(StoreProductsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Product product = new Product();
		Product product1 = new Product();

		productRepository.saveAll(List.of(product, product1));
	}
}
