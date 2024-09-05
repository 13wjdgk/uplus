package com.springboot.security;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.springboot.security.config.security.JwtTokenProvider;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class JWTTokenProviderTest {

	@Autowired
	JwtTokenProvider jwtTokenProvider;

	@Test
	public void testDI() {
		log.info("jwtTokenProvider = " + jwtTokenProvider);
		assertNotNull(jwtTokenProvider);
	}

	@Test
	public void testCreateToken() {
		String userId = "dskim";
		List<String> userRoles = Arrays.asList("ROLE_USER");
		String jwt = jwtTokenProvider.createToken(userId, userRoles);
		log.info("jwt = " + jwt);
		//eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkc2tpbSIsInJvbGVzIjpbIlJPTEVfVVNFUiJdLCJpYXQiOjE3MjU1MTA4NDgsImV4cCI6MTcyNTUxNDQ0OH0.C-q-4X5hBm5M8THQSlIzOGC5JLnBbc-hHaly-6q9a74

		//assertNotNull(jwtTokenProvider);
	}

	@Test
	void testGetUsername(){

		String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkc2tpbSIsInJvbGVzIjpbIlJPTEVfVVNFUiJdLCJpYXQiOjE3MjU1MTA4NDgsImV4cCI6MTcyNTUxNDQ0OH0.C-q-4X5hBm5M8THQSlIzOGC5JLnBbc-hHaly-6q9a74";
		String username = jwtTokenProvider.getUsername(jwt);
		log.info("username = " + username);
		assertEquals("dskim", username);
	}

	@Test
	void testValidateToken(){

		String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkc2tpbSIsInJvbGVzIjpbIlJPTEVfVVNFUiJdLCJpYXQiOjE3MjU1MTA4NDgsImV4cCI6MTcyNTUxNDQ0OH0.C-q-4X5hBm5M8THQSlIzOGC5JLnBbc-hHaly-6q9a74";
		assertTrue(jwtTokenProvider.validateToken(jwt));
	}

	@Test
	void testResolveToekn(){
		String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkc2tpbSIsInJvbGVzIjpbIlJPTEVfVVNFUiJdLCJpYXQiOjE3MjU1MTA4NDgsImV4cCI6MTcyNTUxNDQ0OH0.C-q-4X5hBm5M8THQSlIzOGC5JLnBbc-hHaly-6q9a74";

		// HttpServletRequest의 header에 담긴 custom header의 key,value를 처리
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		Mockito.when(request.getHeader("X-AUTH-TOKEN")).thenReturn(jwt);
		String result = jwtTokenProvider.resolveToken(request);
		assertEquals(jwt,result);
	}
}
