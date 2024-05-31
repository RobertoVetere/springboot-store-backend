package com.hlidevs.store_products;

import com.hlidevs.store_products.entities.Product;
import com.hlidevs.store_products.entities.User;
import com.hlidevs.store_products.enums.Role;
import com.hlidevs.store_products.repositories.ProductRepository;
import com.hlidevs.store_products.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class StoreProductsApplication implements CommandLineRunner {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(StoreProductsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Product product = new Product();
		Product product1 = new Product();
		String adminPassword = passwordEncoder.encode("adminPass");
		String userPassword = passwordEncoder.encode("userPass");
		User admin = new User("sudo", adminPassword, Role.ADMIN );
		User user = new User("user",userPassword, Role.USER );

		userRepository.saveAll(List.of(admin,user));
		productRepository.saveAll(List.of(product, product1));
	}
}
