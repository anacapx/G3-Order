package com.g3.order.service.interfaces;

import java.util.List;

import com.g3.order.controller.dto.OrderDTO;
import com.g3.order.controller.form.OrderForm;
import com.g3.order.model.Order;

public interface IOrderService {

	public List<Order> getAllOrders();
	public OrderDTO addNewOrder(OrderForm orderForm);

}
