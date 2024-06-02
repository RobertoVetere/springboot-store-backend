package com.hlidevs.store_products;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hlidevs.store_products.entities.Product;
import com.hlidevs.store_products.entities.User;
import com.hlidevs.store_products.enums.Role;
import com.hlidevs.store_products.repositories.UserRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.method.AuthorizeReturnObject;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class StoreProductsApplicationTests {

	@Autowired
	MockMvc mock;

	@Autowired
	UserRepository userRepository;

	@Autowired
	private UserDetailsService userDetailsService;


	Product product;

	User user;

	User normalUser;

	@BeforeEach
	void setUp() {
		user = new User("rob","admin", Role.ADMIN);
		userRepository.save(user);

		normalUser = new User("normal", "normalpassword", Role.USER);
		userRepository.save(normalUser);

		UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUserName());
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, user.getPassword(), userDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void contextLoads() {
	}

	@Test
	void testEndPointExpectedHola() throws Exception {
		MvcResult result = mock.perform(MockMvcRequestBuilders.get("/products/hola"))
				.andExpect(status().isOk()).andReturn();

		assertEquals("Hola", result.getResponse().getContentAsString());
		assertNotEquals("hola_", result.getResponse().getContentAsString());
	}

	@Test
	void testEndPointExpectedProduct() throws Exception {
		MvcResult result = mock.perform(MockMvcRequestBuilders.get("/products/all"))
				.andExpect(status().isOk())
				.andReturn();

		String jsonResponse = result.getResponse().getContentAsString();
		List<Product> productList = objectMapper.readValue(jsonResponse, new TypeReference<>() {});

		assertEquals(1, productList.get(0).getId());
	}

	@Test
	void testEndPointExpected403() throws Exception {
		UserDetails userDetails = userDetailsService.loadUserByUsername(normalUser.getUserName());
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, normalUser.getPassword(), userDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);

		mock.perform(MockMvcRequestBuilders.get("/products/all"))
				.andExpect(status().isForbidden());
	}

	@AfterEach
	void tearDown() {
		userRepository.delete(user);
		SecurityContextHolder.clearContext();
	}

}
