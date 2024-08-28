package com.example.springboothtmlregister.user.dto;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class UserDto {
	private long id;

	private String name;
	private String password;
	private String email;
	private Map<Integer,String> roles = new HashMap();
}
