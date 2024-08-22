package com.mycom.myapp.ManyToMany.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

//Non owner Entity
@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	// 1~9번 , 기본
	@ManyToMany(mappedBy = "users" )
	private List<Team> teams;

	// D
	// @ManyToMany(mappedBy = "users" ,fetch = FetchType.EAGER)
	// private List<Team> teams;

	//10번
	// @ManyToMany(mappedBy = "users" ,cascade = CascadeType.PERSIST)
	// private List<Team> teams;

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

	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

	@Override
	public String toString() {
		return "User{" +
			"id=" + id +
			", name='" + name + '\'' +
			'}';
	}
}
