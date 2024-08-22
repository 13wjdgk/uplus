package com.mycom.myapp.JPQL_SELECT.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
	private int id;
	private String name;
	private Long price;
	private int quantity;
	private String country;

	public Product(String name, Long price, int quantity, String country) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.country = country;
	}

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

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "Product{" +
			"id=" + id +
			", name='" + name + '\'' +
			", price=" + price +
			", quantity=" + quantity +
			", country='" + country + '\'' +
			'}';
	}
}
