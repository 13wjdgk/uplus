package com.example.springjpahtmllogin.user.service;

import com.example.springjpahtmllogin.user.dto.UserResultDto;


public interface UserService {
	UserResultDto login(String email, String password);
}
