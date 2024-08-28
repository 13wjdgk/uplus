package com.example.springboothtmlregister.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboothtmlregister.user.entity.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
	UserRole findByName(String name);


}
