package com.mycom.myapp.ManyToMany.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "teams")
public class Team {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;

	// // # 1번 ~8번 ,10번 ,C
	@ManyToMany
	// 다대다 두 엔티티를 연결해주는 중간 테이블 이름 / 중간 테이블에 생길(현재 오너) 엔티티를 가리키는 컬럼, 중간 테이블에 생길(상대 엔티티)를 가리키는 컬럼
	@JoinTable(name = "teams_users", joinColumns = @JoinColumn(name = "team_id"),inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<User> users;

	// 9번 ,A
	// @ManyToMany(cascade =  CascadeType.PERSIST)
	// // 다대다 두 엔티티를 연결해주는 중간 테이블 이름 / 중간 테이블에 생길(현재 오너) 엔티티를 가리키는 컬럼, 중간 테이블에 생길(상대 엔티티)를 가리키는 컬럼
	// @JoinTable(name = "teams_users", joinColumns = @JoinColumn(name = "team_id"),inverseJoinColumns = @JoinColumn(name = "user_id"))
	// private List<User> users;

	// //B
	// @ManyToMany(fetch = FetchType.EAGER)
	// // 다대다 두 엔티티를 연결해주는 중간 테이블 이름 / 중간 테이블에 생길(현재 오너) 엔티티를 가리키는 컬럼, 중간 테이블에 생길(상대 엔티티)를 가리키는 컬럼
	// @JoinTable(name = "teams_users", joinColumns = @JoinColumn(name = "team_id"),inverseJoinColumns = @JoinColumn(name = "user_id"))
	// private List<User> users;

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

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Team{" +
			"id=" + id +
			", name='" + name + '\'' +
			'}';
	}
}
