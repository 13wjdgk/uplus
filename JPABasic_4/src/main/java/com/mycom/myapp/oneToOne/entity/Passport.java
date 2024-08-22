package com.mycom.myapp.oneToOne.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Passport {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String number;
	@OneToOne(mappedBy = "passport") //OneToONe 관계의 Owner Entity Person에서 @JoinColumn의 필드값
	private Person person;

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	// @OneToOne(mappedBy = "passport",cascade = CascadeType.PERSIST) //OneToONe 관계의 Owner Entity Person에서 @JoinColumn의 필드값
	// private Person person;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "Passport{" +
			"id=" + id +
			", number='" + number + '\'' +
			// ", person=" + person +
			'}';
	}
}
