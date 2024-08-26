package com.mycom.springbootjpacrudfind.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mycom.springbootjpacrudfind.entity.Student;
import com.mycom.springbootjpacrudfind.service.StudentServiceFind;

import lombok.RequiredArgsConstructor;

@Controller
@ResponseBody
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentControllerFind {
	private final StudentServiceFind studentServiceFind;
	@GetMapping("/find/name")
	public List<Student> findStudentByName(String name) {
		return studentServiceFind.findStudentByName(name);
	}

	@GetMapping("/find/emailphone")
	public List<Student> findEmailAndPhone(String email, String phone) {
		return studentServiceFind.findByEmailAndPhone(email, phone);
	}
	@GetMapping("/find/emailOrPhone")
	public List<Student> findEmailOrPhone(String email, String phone) {
		return studentServiceFind.findByEmailOrPhone(email, phone);
	}

	@GetMapping("/find/startingwith")
	public List<Student> findStartingWith(String name) {
		return studentServiceFind.findByNameStartingWith(name);
	}

	@GetMapping("/find/endingwith")
	public List<Student> findEndingWith(String email) {
		return studentServiceFind.findByEmailEndingWith(email);
	}

	@GetMapping("/find/phonecontaining")
	public List<Student> findphoneContaining(String phone) {
		return studentServiceFind.findByPhoneContaining(phone);
	}

	@GetMapping("/find/namelike")
	public List<Student> findBynameLike(String name) {
		return studentServiceFind.findByNameLike(name);
	}
	@GetMapping("/find/allbyorderbyname")
	public List<Student> findAllByOrderByName() {
		return studentServiceFind.findAllByOrderByNameDesc();
	}

	@GetMapping("/find/idbetweenstartend")
	public List<Student> findByIdBetweenStartAndEnd(int start, int end) {
		return studentServiceFind.findByIdBetween(start, end);
	}

}
