package com.g3.order.service.interfaces;

import java.util.List;

import com.g3.order.controller.dto.OrderDTO;
import com.g3.order.controller.form.OrderForm;

public interface IOrderService {

	public List<OrderDTO> getAllOrders();
	public OrderDTO createOrder(OrderForm orderForm);

}
