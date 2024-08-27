package com.example.springjpahtmllogin.user.controller.userController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.springjpahtmllogin.user.dto.UserResultDto;
import com.example.springjpahtmllogin.user.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@ResponseBody
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;

	@PostMapping("/login")
	public UserResultDto login( @RequestParam("email") String email, @RequestParam("password") String password, HttpSession session) {
		UserResultDto userResultDto = userService.login(email, password);
		if(userResultDto.getResult().equals("success")) {
			session.setAttribute("userDto", userResultDto.getUserDto());
			return userResultDto;
		}

		return userService.login(email, password);
	}

	@GetMapping("/logout")
	public UserResultDto logout( HttpSession session) {
		UserResultDto userResultDto = new UserResultDto();
		try{
			session.invalidate();
			userResultDto.setResult("success");
		}catch (IllegalStateException e) {
			userResultDto.setResult("fail");

		}
		return userResultDto;

	}
}
