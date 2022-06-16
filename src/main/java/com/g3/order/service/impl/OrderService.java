package com.g3.order.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.g3.order.controller.dto.NewOrderDTO;
import com.g3.order.controller.dto.OrderDTO;
import com.g3.order.controller.form.OrderForm;
import com.g3.order.exception.custom.ResourceNotFoundException;
import com.g3.order.model.Order;
import com.g3.order.model.User;
import com.g3.order.repository.OrderRepository;
import com.g3.order.service.interfaces.IOrderService;

@Service
@Component
public class OrderService implements IOrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public List<OrderDTO> getAllOrders(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Order> orders = orderRepository.findAll(pageable);
		List<OrderDTO> ordersDTO = new ArrayList<>();
		for (Order o : orders) {
			OrderDTO orderDTO = new OrderDTO(o);
			ordersDTO.add(orderDTO);
		}
		return ordersDTO;
	}

	@Override
	public NewOrderDTO createOrder(OrderForm orderForm, HttpServletRequest httpServletRequest) {
		try {
			User user = RestService.getUserById(orderForm.getUserId(), httpServletRequest);		
			if (user.getName() == null) {
				throw new ResourceNotFoundException("User not found.");
			}
			Order order = orderRepository.save(orderForm.toOrder());
			NewOrderDTO orderDTO = new NewOrderDTO(order, user);
			return orderDTO;						
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
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
