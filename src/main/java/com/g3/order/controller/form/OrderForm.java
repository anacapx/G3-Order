package com.g3.order.controller.form;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.g3.order.model.Order;

public class OrderForm {

	@NotNull @DecimalMin(value = "1")
	private Long userId;
	
	@NotNull @DecimalMin(value = "0")
	private Double value;
	
	@NotNull @NotEmpty
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
