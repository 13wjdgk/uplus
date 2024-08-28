package com.example.springboothtmluseraddress.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboothtmluseraddress.user.entity.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
	UserRole findByName(String name);


}
