package com.example.springboothtmlregister.user.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.springboothtmlregister.user.dto.UserDto;
import com.example.springboothtmlregister.user.dto.UserResultDto;
import com.example.springboothtmlregister.user.entity.User;
import com.example.springboothtmlregister.user.entity.UserRole;
import com.example.springboothtmlregister.user.repository.UserRepository;
import com.example.springboothtmlregister.user.repository.UserRoleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	private final UserRepository userRepository;
	private final UserRoleRepository userRoleRepository;
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

	@Override
	public UserResultDto insertUser(User user) {
		UserResultDto userResultDto = new UserResultDto();

		try{
			UserRole userRole = userRoleRepository.findByName("role_customer");
			user.getRoles().add(userRole);
			userRepository.save(user);
			userResultDto.setResult("success");

		}catch (Exception e) {
			userResultDto.setResult("fail");
		}


		return userResultDto;
	}
}
