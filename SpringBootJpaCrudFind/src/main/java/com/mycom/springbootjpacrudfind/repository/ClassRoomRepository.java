package com.mycom.springbootjpacrudfind.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycom.springbootjpacrudfind.entity.ClassRoom;

public interface ClassRoomRepository extends JpaRepository<ClassRoom, Integer> {
}
