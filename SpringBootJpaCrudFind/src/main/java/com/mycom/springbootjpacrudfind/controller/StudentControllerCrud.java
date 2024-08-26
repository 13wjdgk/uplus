package com.mycom.springbootjpacrudfind.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mycom.springbootjpacrudfind.entity.Student;
import com.mycom.springbootjpacrudfind.service.StudentServiceCrud;

import lombok.RequiredArgsConstructor;

@Controller
@ResponseBody
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentControllerCrud {

	private final StudentServiceCrud studentServiceCrud;

	@GetMapping("/list")
	public List<Student> listStudent() {
		return studentServiceCrud.listStudent();
	}

	@GetMapping("/detail/{id}")
	public Optional<Student> detailStudent(@PathVariable int id) {
		return studentServiceCrud.detailStudent(id);
	}

	@GetMapping("/count")
	public long countStudent() {
		return studentServiceCrud.countStudent();
	}

	@PostMapping("/insert")
	public Student insertStudent(Student student) {
		return studentServiceCrud.insertStudent(student);
	}

	@PostMapping("/update")
	public Student updateStudent(Student student) {
		return studentServiceCrud.updateStudent(student);
	}

	@GetMapping("/delete/{id}")
	public void deleteStudent(@PathVariable int id) {
		studentServiceCrud.deleteStudent(id);
	}

	@GetMapping("/page")
	public List<Student> listStudent(@RequestParam int pageNumber, @RequestParam int pageSize) {
		return studentServiceCrud.listStudent(pageNumber, pageSize);
	}
}
