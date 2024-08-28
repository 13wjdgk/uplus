package com.example.springboothtmluseraddress.user.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.springboothtmluseraddress.user.entity.UserAddress;

import lombok.Data;

@Data
public class UserDto {
	private long id;

	private String name;
	private String password;
	private String email;
	private Map<Integer,String> roles = new HashMap();
	private List<UserAddressDto> addresses = new ArrayList<>();

}

