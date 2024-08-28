package com.example.springboothtmlregister.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboothtmlregister.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);
}
