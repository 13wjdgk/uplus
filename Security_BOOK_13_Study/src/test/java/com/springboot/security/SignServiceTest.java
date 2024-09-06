package com.springboot.security;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import com.springboot.security.data.dto.SignInResultDto;
import com.springboot.security.data.dto.SignUpResultDto;
import com.springboot.security.service.SignService;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class SignServiceTest {
	@Autowired
	SignService signService;

	@Test
	void testDI() {
		log.info("signService = " + signService);
		assertNotNull(signService);
	}

	@Test
	void SignUp() {
		String id = "test2";
		String password = "1234";
		String name = "test";
		String role = "ROLE_UPLUS";
		assertNotNull(signService);
		SignUpResultDto signUpResultDto = signService.signUp(id, password, name, role);
		log.info("signInResultDto = " + signUpResultDto);
	}

	@Test
	void SignIn() {
		String id = "test2";
		String password = "1234";
		assertNotNull(signService);
		SignInResultDto signInResultDto = signService.signIn(id, password);
		log.info("signInResultDto = " + signInResultDto.getToken());
	}
}
