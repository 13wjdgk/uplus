package com.mycom.myapp.oneToOne.entity.key;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class StudentKey implements Serializable {
private String code;
private Long number;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		StudentKey that = (StudentKey)o;
		return Objects.equals(code, that.code) && Objects.equals(number, that.number);
	}

	@Override
	public int hashCode() {
		return Objects.hash(code, number);
	}
}
