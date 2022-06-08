package com.g3.order.controller.form;

import com.g3.order.model.Order;

public class OrderForm {

	private Long userId;
	private Double value;
	private String products;
	
	public OrderForm(Long userId, Double value, String products) {
		super();
		this.userId = userId;
		this.value = value;
		this.products = products;
	}

	public Order toOrder() {
		return new Order(this.userId, this.value, this.products);
	}
}
