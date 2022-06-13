package com.g3.order.controller.dto;

import java.sql.Timestamp;

import com.g3.order.model.Order;
import com.g3.order.model.User;
import com.g3.order.model.enums.OrderEnum;
import com.g3.order.service.impl.RestService;

public class OrderDTO {

	private Long id;
	private User user;
	private Double value;
	private String products;
	private Timestamp date;
	private OrderEnum status;
	
	public OrderDTO(Order order) {
		try {
			User user = RestService.getUserById(order.getUserId());
			this.user = user;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		this.id = order.getOrderId();
		this.value = order.getValue();
		this.products = order.getProducts();
		this.date = order.getDate();
		this.status = order.getStatus();
	}
	
	public User getUser() {
		return user;
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
