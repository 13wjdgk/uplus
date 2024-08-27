package com.example.springjpahtmllogin.user.dto;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.example.springjpahtmllogin.user.entity.UserRole;

import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.ToString;

@Data
public class UserDto {
	private long id;

	private String name;
	private String password;
	private String email;
	private Map<Integer,String> roles = new HashMap();
}
