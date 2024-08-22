package com.mycom.myapp.jpql_subquery.entity;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

//owner
@Entity
public class Orders {
	@Id
	@GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
	private int id;
	@Column(name = "order_quantity")
	private int orderQuantity;
	@Column(name = "order_date")
	private LocalDate orderDate;
	@ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
	private Customer customer; //Order가 Many

	@ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
	private Product product;


	public Orders() {
	}

	public Orders(Customer customer, Product product, int orderQuantity, LocalDate orderDate ) {
		this.orderQuantity = orderQuantity;
		this.orderDate = orderDate;
		this.customer = customer;
		this.product = product;
	}

	@Override
	public String toString() {
		return "Orders{" +
			"id=" + id +
			", orderQuantity=" + orderQuantity +
			", orderDate=" + orderDate +
			'}';
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}
