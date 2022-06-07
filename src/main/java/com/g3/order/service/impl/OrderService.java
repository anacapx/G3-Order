package com.g3.order.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.g3.order.controller.dto.OrderDTO;
import com.g3.order.controller.form.OrderForm;
import com.g3.order.model.Order;
import com.g3.order.service.interfaces.IOrderService;

@Service
@Component
public class OrderService implements IOrderService {

	@Override
	public List<Order> getAllOrders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderDTO addNewOrder(OrderForm orderForm) {
		// TODO Auto-generated method stub
		return null;
	}

}
