package com.hlidevs.store_products.controllers;
import com.hlidevs.store_products.entities.Product;
import com.hlidevs.store_products.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    ProductService productService;

    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/hola")
    public String hola() {
        return "Hola";
    }
}
