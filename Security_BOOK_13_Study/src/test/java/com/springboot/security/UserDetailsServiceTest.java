package com.springboot.security;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
@Transactional
public class UserDetailsServiceTest {
	@Autowired
	UserDetailsService userDetailsService;
	@Autowired
	PasswordEncoder passwordEncoder;

	@Test
	void testDI() {
		log.info("userDetailsService = " + userDetailsService);
		assertNotNull(passwordEncoder);
	}
	@Test
	void testLoadUserByUsername() {
		UserDetails userDetails = userDetailsService.loadUserByUsername("1");
		String password = "1234";
		log.info("User = " +userDetails.getUsername());
		log.info("Password = " +userDetails.getPassword());
		log.info("Authorities = " +userDetails.getAuthorities());
		assertTrue(passwordEncoder.matches(password, userDetails.getPassword()));

	}


}
