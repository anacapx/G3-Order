package com.g3.order.service.interfaces;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.g3.order.controller.dto.NewOrderDTO;
import com.g3.order.controller.dto.OrderDTO;
import com.g3.order.controller.form.OrderForm;

public interface IOrderService {

	public List<OrderDTO> getAllOrders(int page, int size);
	public NewOrderDTO createOrder(OrderForm orderForm, HttpServletRequest httpServletRequest);
	public OrderDTO getOrderById(Long id);

}
