package com.mycom.springbootjpacrudfind.service;

import java.util.List;
import java.util.Optional;

import com.mycom.springbootjpacrudfind.entity.ClassRoom;
import com.mycom.springbootjpacrudfind.entity.Student;

public interface ClassRoomService {
	public List<ClassRoom> listClass();
	public long countClass();
	Optional<ClassRoom> detailClass(int id);

	ClassRoom insertStudent(ClassRoom classRoom);



}
