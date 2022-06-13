package com.g3.order.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.g3.order.controller.dto.OrderDTO;
import com.g3.order.controller.form.OrderForm;
import com.g3.order.exception.custom.ResourceNotFoundException;
import com.g3.order.model.Order;
import com.g3.order.repository.OrderRepository;
import com.g3.order.service.interfaces.IOrderService;

@Service
@Component
public class OrderService implements IOrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public List<OrderDTO> getAllOrders() {
		List<Order> orders = orderRepository.findAll();
		List<OrderDTO> ordersDTO = new ArrayList<>();
		for (Order o : orders) {
			OrderDTO orderDTO = new OrderDTO(o);
			ordersDTO.add(orderDTO);
		}
		return ordersDTO;
	}

	@Override
	public OrderDTO createOrder(OrderForm orderForm) {
		Order order = orderRepository.save(orderForm.toOrder());
			OrderDTO orderDTO = new OrderDTO(order);
			return orderDTO;			
	}

	@Override
	public OrderDTO getOrderById(Long id) {
		Optional<Order> order = orderRepository.findById(id);
		if (order.isPresent()) {
			return new OrderDTO(order.get());
		}
		throw new ResourceNotFoundException("Order not found.");
	}

}
