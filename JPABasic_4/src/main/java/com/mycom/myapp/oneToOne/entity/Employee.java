package com.mycom.myapp.oneToOne.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// Key 생성
// 운영 시는 DB에 테이블 설계에 따라 테이블 생성하고 그 DBMS와 테이블에 맞는 key 설정
@Entity
@Table(name = "employee")
public class Employee {
	
	@Id
	@Column(name="id")
	//@GeneratedValue(strategy = GenerationType.AUTO) // jpa의 기본 전략에 의존, Mysql은 별도의 seq 테이블을 생성해서 관리하는데 , AUTO를 사용하는 것이 베스트
	@GeneratedValue(strategy = GenerationType.IDENTITY) // DBMS의 auto increment 기능 사용
	private int id;
	private String name;
	private String address;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", address=" + address + "]";
	}
}
