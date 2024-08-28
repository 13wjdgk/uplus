package com.example.springboothtmlregister.user.service;

import com.example.springboothtmlregister.user.dto.UserResultDto;
import com.example.springboothtmlregister.user.entity.User;

public interface UserService {
	UserResultDto login(String email, String password);
	UserResultDto insertUser(User user);
}
