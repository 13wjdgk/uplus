package com.springboot.security;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class PasswordEncoderTest {
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Test
	public void testDI() {
		log.info("passwordEncoder = " + passwordEncoder);
		assertNotNull(passwordEncoder);
	}

	@Test
	public void testName() {
		log.info(passwordEncoder.getClass().getName());
		//assertNotNull(passwordEncoder);
	}
	@Test
	public void testPasswordEncoder() {
		String originalPassword = "password";
		String encodedPassword = passwordEncoder.encode(originalPassword);
		log.info("password = " + encodedPassword);
		assertTrue(passwordEncoder.matches(originalPassword, encodedPassword));
		//assertNotNull(passwordEncoder);
	}
}
