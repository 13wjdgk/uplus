package com.example.springboothtmlregister.user.dto;

import java.util.List;

import lombok.Data;

@Data
public class UserResultDto {
	private String result;
	private UserDto userDto;
	private List<UserDto> userList;
}
