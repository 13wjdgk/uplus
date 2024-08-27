package com.example.springjpahtmllogin.user.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.springjpahtmllogin.user.dto.UserDto;
import com.example.springjpahtmllogin.user.dto.UserResultDto;
import com.example.springjpahtmllogin.user.entity.User;
import com.example.springjpahtmllogin.user.entity.UserRole;
import com.example.springjpahtmllogin.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	private final UserRepository userRepository;
	@Override
	public UserResultDto login(String email, String password) {
		UserResultDto userResultDto = new UserResultDto();
		User user = userRepository.findByEmail(email);
		if(user != null && user.getPassword().equals(password)) {
			userResultDto.setResult("success");
			UserDto userDto = new UserDto();
			userDto.setId(user.getId());
			userDto.setName(user.getName());
			userDto.setEmail(user.getEmail());
			userDto.setPassword(user.getPassword());
			Map<Integer,String> userRoles = userDto.getRoles();
			for(UserRole userRole : user.getRoles()) {
				userRoles.put((int)userRole.getId(), userRole.getName());
			}
			userResultDto.setUserDto(userDto);
			return userResultDto;
		}
		userResultDto.setResult("fail");
		return userResultDto;
	}
}
