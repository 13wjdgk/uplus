package com.example.springboothtmluseraddress.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboothtmluseraddress.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);
}
