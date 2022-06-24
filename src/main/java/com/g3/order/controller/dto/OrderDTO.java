package com.g3.order.controller.dto;

import java.sql.Timestamp;

import com.g3.order.model.Order;
import com.g3.order.model.User;
import com.g3.order.model.enums.OrderEnum;
import com.g3.order.service.impl.RestService;

public class OrderDTO {

	private Long id;
	private Long userId;
	private Double value;
	private String products;
	private Timestamp date;
	private OrderEnum status;
	
	public OrderDTO(Order order) {
		this.id = order.getOrderId();
		this.userId = order.getUserId();
		this.value = order.getValue();
		this.products = order.getProducts();
		this.date = order.getDate();
		this.status = order.getStatus();
	}

	public Long getUserId() {
		return userId;
	}
	public Long getId() {
		return id;
	}
	public Double getValue() {
		return value;
	}
	public String getProducts() {
		return products;
	}
	public Timestamp getDate() {
		return date;
	}
	public OrderEnum getStatus() {
		return status;
	}
	
}
