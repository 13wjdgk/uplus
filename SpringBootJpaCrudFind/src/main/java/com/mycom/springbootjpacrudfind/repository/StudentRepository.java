package com.mycom.springbootjpacrudfind.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycom.springbootjpacrudfind.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

	// find() : 직접 명시
	List<Student> findByName(String name);
	List<Student> findByEmailAndPhone(String email, String phone);
	List<Student> findByEmailOrPhone(String email, String phone);
	List<Student> findByNameStartingWith(String name);

	List<Student> findByEmailEndingWith(String email);
	List<Student> findByPhoneContaining(String phone);
	List<Student> findByNameLike(String name);

	//orderBy() : 정렬
	List<Student> findAllByOrderByNameDesc();

	List<Student> findByIdBetween(int start, int end);
}
