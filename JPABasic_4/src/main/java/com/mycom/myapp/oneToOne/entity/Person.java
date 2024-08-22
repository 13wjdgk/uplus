package com.mycom.myapp.oneToOne.entity;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;

	// @OneToOne(fetch = FetchType.LAZY )
	// @JoinColumn(name = "passport")
	// private Passport passport;

	// @OneToOne(cascade =  CascadeType.PERSIST )
	// @JoinColumn(name = "passport")
	// private Passport passport;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "passport")
	private Passport passport;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Passport getPassport() {
		return passport;
	}

	public void setPassport(Passport passport) {
		this.passport = passport;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Person person = (Person)o;
		return Objects.equals(id, person.id) && Objects.equals(name, person.name) && Objects.equals(passport, person.passport);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, passport);
	}

	@Override
	public String toString() {
		return "Person{" +
			"id=" + id +
			", name='" + name + '\'' +
			'}';
	}
}
