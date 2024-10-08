package com.example.springboothtmluseraddress.user.service;

import com.example.springboothtmluseraddress.user.dto.UserResultDto;
import com.example.springboothtmluseraddress.user.entity.User;
import com.example.springboothtmluseraddress.user.entity.UserAddress;

public interface UserService {
	UserResultDto login(String email, String password);
	UserResultDto insertUser(User user);
	UserResultDto detailUser(long id);
	UserResultDto listUserAddress(long id);
	UserResultDto insertUserAddress(UserAddress userAddress, long id);
}
