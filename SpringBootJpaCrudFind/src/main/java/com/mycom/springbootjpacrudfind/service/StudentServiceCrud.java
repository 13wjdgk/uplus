package com.mycom.springbootjpacrudfind.service;

import java.util.List;
import java.util.Optional;

import com.mycom.springbootjpacrudfind.entity.Student;

public interface StudentServiceCrud {

	public List<Student> listStudent();
	public long countStudent();
	Optional<Student> detailStudent(int id);

	Student insertStudent(Student student);

	Student updateStudent(Student student);

	public void deleteStudent(int id);

	List<Student> listStudent(int pageNumber,int pageSize);


}
