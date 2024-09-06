package com.springboot.security.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.security.data.entity.User;

// 예제 13.7
public interface UserRepository extends JpaRepository<User, Long> {

    User getByUid(String uid);

}