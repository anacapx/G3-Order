package com.g3.order.service.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.g3.order.exception.custom.ResourceNotFoundException;
import com.g3.order.repository.OrderRepository;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderservice;

    @Test
    void itShouldReturnAnOrdersList() {
        // When
        orderservice.getAllOrders();
        // Then
        verify(orderRepository).findAll();
    }

    @Test
    void itShouldThorwsAnResourceNotFoundExceptionWhenAnInvalidIdIsPassed() {
        // Given
        Long id = (long) 11;
        // When
        when(orderRepository.findById(id)).thenThrow(ResourceNotFoundException.class);
        // Then
        assertThrows(ResourceNotFoundException.class, () -> orderservice.getOrderById(id));
    }
}

