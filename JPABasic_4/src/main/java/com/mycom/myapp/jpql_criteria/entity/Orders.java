package com.mycom.myapp.jpql_criteria.entity;

import java.time.LocalDate;

import com.mycom.myapp.jpql_named_native.entity.Product;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;

//owner
@Entity
@NamedQueries({
	@NamedQuery(name = "Orders.findByCustomerName",
		query = """
				select o from Orders o join o.customer c where c.name = :customerName
				"""),
	@NamedQuery(name = "Orders.findByOrderDate",
		query = """
				select o from Orders o where o.orderDate = :orderDate
				"""),
	@NamedQuery(name = "Orders.findByOrderDateRange",
		query = """
				select o from Orders o where o.orderDate between :startDate and :endDate
				"""),
	@NamedQuery(name = "Orders.findByOrderProductPriceRange",
		query = """
				select o from Orders o left join Product p on o.product = p where p.price between :startPrice and :endPrice
				""")


})
public class Orders {
	@Id
	@GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
	private int id;
	@Column(name = "order_quantity")
	private int orderQuantity;
	@Column(name = "order_date")
	private LocalDate orderDate;
	@ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
	private com.mycom.myapp.jpql_criteria.entity.Customer customer; //Order가 Many

	@ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
	private com.mycom.myapp.jpql_criteria.entity.Product product;



	public Orders(com.mycom.myapp.jpql_criteria.entity.Customer customer, com.mycom.myapp.jpql_criteria.entity.Product product, int orderQuantity, LocalDate orderDate ) {
		this.orderQuantity = orderQuantity;
		this.orderDate = orderDate;
		this.customer = customer;
		this.product = product;
	}

	public Orders() {

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

	public com.mycom.myapp.jpql_criteria.entity.Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public com.mycom.myapp.jpql_criteria.entity.Product getProduct() {
		return product;
	}

	public void setProduct(com.mycom.myapp.jpql_criteria.entity.Product product) {
		this.product = product;
	}
}
