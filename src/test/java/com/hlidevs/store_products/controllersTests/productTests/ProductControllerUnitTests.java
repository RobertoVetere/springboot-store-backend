package com.hlidevs.store_products.controllersTests.productTests;

import com.hlidevs.store_products.controllers.ProductController;
import com.hlidevs.store_products.entities.Product;
import com.hlidevs.store_products.services.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;

@AutoConfigureMockMvc
@SpringBootTest
public class ProductControllerUnitTests {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;


}
