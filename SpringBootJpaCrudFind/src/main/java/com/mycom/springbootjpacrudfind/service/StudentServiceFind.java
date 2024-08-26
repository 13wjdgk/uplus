package com.mycom.springbootjpacrudfind.service;

import java.util.List;

import com.mycom.springbootjpacrudfind.entity.Student;

public interface StudentServiceFind {
	List<Student> findStudentByName(String name);
	List<Student> findByEmailAndPhone(String email, String phone);
	List<Student> findByEmailOrPhone(String email, String phone);
	List<Student> findByNameStartingWith(String name);
	List<Student> findByEmailEndingWith(String email);
	List<Student> findByPhoneContaining(String phone);
	List<Student> findByNameLike(String name);
	List<Student> findAllByOrderByNameDesc();
	List<Student> findByIdBetween(int start, int end);
}
