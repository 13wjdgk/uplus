package com.mycom.springbootjpacrudfind.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mycom.springbootjpacrudfind.entity.ClassRoom;
import com.mycom.springbootjpacrudfind.entity.Student;
import com.mycom.springbootjpacrudfind.repository.ClassRoomRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClassRoomServiceImpl implements ClassRoomService {
	private final ClassRoomRepository classRoomRepository;
	@Override
	public List<ClassRoom> listClass() {
		return classRoomRepository.findAll();
	}

	@Override
	public long countClass() {
		return classRoomRepository.count();
	}

	@Override
	public Optional<ClassRoom> detailClass(int id) {
		return classRoomRepository.findById(id);
	}

	@Override
	public ClassRoom insertStudent(ClassRoom classRoom) {
		return classRoomRepository.save(classRoom);
	}
}
