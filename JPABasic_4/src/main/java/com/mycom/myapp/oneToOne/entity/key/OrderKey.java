package com.mycom.myapp.oneToOne.entity.key;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class OrderKey implements Serializable {
	private int productId;
	private int customerId;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		OrderKey orderKey = (OrderKey)o;
		return productId == orderKey.productId && customerId == orderKey.customerId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(productId, customerId);
	}
}
