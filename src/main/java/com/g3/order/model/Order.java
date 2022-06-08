package com.g3.order.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.g3.order.model.enums.OrderEnum;

@Entity
@Table(name = "tb_order")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "orderId", nullable = false)
	private Long orderId;
	
	@Column(name = "user_id", nullable = false)
	private Long userId;
	
	@Column(name = "order_total", nullable = false)
	private Double value;
	
	@Column(name = "order_products", nullable = false)
	private String products;
	
	@Column(name = "order_date", nullable = false)
	private Timestamp date = new Timestamp(System.currentTimeMillis());
	
	@Column(name = "order_status", nullable = false)
	@Enumerated(EnumType.STRING)
	private OrderEnum status = OrderEnum.PENDING;
	
	public Order() {
	}
	
	public Order(Long userId, Double value, String products) {
		this.userId = userId;
		this.value = value;
		this.products = products;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String getProducts() {
		return products;
	}

	public void setProducts(String products) {
		this.products = products;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public OrderEnum getStatus() {
		return status;
	}

	public void setStatus(OrderEnum status) {
		this.status = status;
	}
	
}
