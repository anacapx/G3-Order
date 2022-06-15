package com.g3.order.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.g3.order.controller.dto.NewOrderDTO;
import com.g3.order.controller.dto.OrderDTO;
import com.g3.order.controller.form.OrderForm;
import com.g3.order.service.impl.KafkaProducerApp;
import com.g3.order.service.impl.KafkaService;
import com.g3.order.service.interfaces.IOrderService;

@RestController
@RequestMapping("/order")
@CrossOrigin("*")
public class OrderController {

	@Autowired
	private IOrderService orderService;

	@GetMapping
	public ResponseEntity<List<OrderDTO>> getAllOrders(@RequestParam(name = "page") int pageValue, @RequestParam(name = "size") int sizeValue) {
		List<OrderDTO> ordersDTOs = orderService.getAllOrders(pageValue, sizeValue);
		return ResponseEntity.ok(ordersDTOs);
	}

	@GetMapping("/{id}")
	public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) {
		OrderDTO orderDTO = orderService.getOrderById(id);
		return ResponseEntity.ok(orderDTO);
	}

	@PostMapping
	public ResponseEntity<NewOrderDTO> createOrder(@RequestBody @Valid OrderForm orderForm,
			UriComponentsBuilder uriBuilder) {
		NewOrderDTO orderDTO = orderService.createOrder(orderForm);
		URI uri = uriBuilder.path("/order/{id}").buildAndExpand(orderDTO.getId()).toUri();
		KafkaProducerApp.produce(orderDTO.getId().toString(), KafkaService.messageConstructor(orderDTO));
		return ResponseEntity.created(uri).body(orderDTO);
	}
}
