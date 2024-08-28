package com.example.springboothtmluseraddress.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboothtmluseraddress.user.entity.UserAddress;

public interface UserAddressRepository extends JpaRepository<UserAddress, Integer> {
	List<UserAddress> findByUserId(Long userId);
}
