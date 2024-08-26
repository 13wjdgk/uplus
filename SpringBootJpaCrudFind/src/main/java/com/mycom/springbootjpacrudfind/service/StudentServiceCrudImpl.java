package com.mycom.springbootjpacrudfind.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mycom.springbootjpacrudfind.entity.Student;
import com.mycom.springbootjpacrudfind.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceCrudImpl implements StudentServiceCrud {

	private final StudentRepository studentRepository;


	@Override
	public List<Student> listStudent() {
		return studentRepository.findAll();

	}

	@Override
	public long countStudent() {
		return studentRepository.count();
	}

	@Override
	public Optional<Student> detailStudent(int id) {
		return studentRepository.findById(id);
	}

	@Override
	public Student insertStudent(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public Student updateStudent(Student student) {
		return studentRepository.save(student);
	}
	@Override
	public void deleteStudent(int id) {
		studentRepository.deleteById(id);
	}

	@Override
	public List<Student> listStudent(int pageNumber, int pageSize) {
		Pageable pageable = (Pageable)PageRequest.of(pageNumber, pageSize);
		Page<Student> page = studentRepository.findAll(pageable);
		return page.toList();
	}
}
