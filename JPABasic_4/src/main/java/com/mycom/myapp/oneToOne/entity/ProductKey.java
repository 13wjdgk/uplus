package com.mycom.myapp.oneToOne.entity;

import java.io.Serializable;
import java.util.Objects;

public class ProductKey implements Serializable {
	private String code;
	private Long number;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ProductKey that = (ProductKey)o;
		return Objects.equals(code, that.code) && Objects.equals(number, that.number);
	}

	@Override
	public int hashCode() {
		return Objects.hash(code, number);
	}

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
}
