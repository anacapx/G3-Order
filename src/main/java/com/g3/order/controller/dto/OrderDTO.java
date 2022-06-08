package com.g3.order.controller.dto;

import java.sql.Timestamp;

import com.g3.order.model.Order;
import com.g3.order.model.enums.OrderEnum;

public class OrderDTO {

	private Long id;
	private String userName;
	private Double value;
	private String products;
	private Timestamp date;
	private OrderEnum status;
	
	public OrderDTO(Order order) {
		this.id = order.getOrderId();
//		Alterar quando houver integração com UserAPI!
		this.userName = "Teste";
		this.value = order.getValue();
		this.products = order.getProducts();
		this.date = order.getDate();
		this.status = order.getStatus();
	}
	
	public Long getId() {
		return id;
	}
	public String getUserName() {
		return userName;
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
