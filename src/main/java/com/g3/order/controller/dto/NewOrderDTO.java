package com.g3.order.controller.dto;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import com.g3.order.model.Order;
import com.g3.order.model.User;
import com.g3.order.model.enums.OrderEnum;
import com.g3.order.service.impl.RestService;

public class NewOrderDTO {

	private Long id;
	private User user;
	private Double value;
	private String products;
	private Timestamp date;
	private OrderEnum status;

	public NewOrderDTO(Order order, HttpServletRequest httpServletRequest) {
		try {
			User user = RestService.getUserById(order.getUserId(), httpServletRequest);
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

	public NewOrderDTO(Order order, User user) {
		this.user = user;
		this.id = order.getOrderId();
		this.value = order.getValue();
		this.products = order.getProducts();
		this.date = order.getDate();
		this.status = order.getStatus();
	}

	public Long getId() {
		return id;
	}

	public User getUser() {
		return user;
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

	@Override
	public String toString() {
		return "NewOrderDTO [id=" + id + ", user=" + user + ", value=" + value + ", products=" + products + ", date="
				+ date + ", status=" + status + "]";
	}

	
}
