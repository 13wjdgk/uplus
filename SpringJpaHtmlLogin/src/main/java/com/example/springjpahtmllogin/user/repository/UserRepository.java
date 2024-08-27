package com.example.springjpahtmllogin.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springjpahtmllogin.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);
}
