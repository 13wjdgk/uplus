package com.mycom.myapp.oneToOne.entity;

import com.mycom.myapp.oneToOne.entity.key.OrderKey;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class Orders {
	@EmbeddedId
	private OrderKey id;

	private int orderCnt;

	public OrderKey getId() {
		return id;
	}

	public void setId(OrderKey id) {
		this.id = id;
	}

	public int getOrderCnt() {
		return orderCnt;
	}

	public void setOrderCnt(int orderCnt) {
		this.orderCnt = orderCnt;
	}

	@Override
	public String toString() {
		return "Order{" +
			"id=" + id.toString() +
			", orderCnt=" + orderCnt +
			'}';
	}
}
