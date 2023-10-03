package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Orders;
import com.example.demo.repository.IOrderRepository;

@SpringBootTest
//@MockBean(value = {OrderService.class})
public class OrderServiceTest2 {
	@Mock
	IOrderRepository orderRepository;
	@InjectMocks
	OrderService orderService;

	@Test
	public void testGetOrders() {
		// Arrange
		List<Orders> orders = new ArrayList<>();
		Orders o1 = new Orders();
		orders.add(o1);
		when(orderRepository.findAll()).thenReturn(orders);
		// act
		List<Orders> list = orderService.getOrders();
//		assert
		assertEquals(1, list.size());
	}
	@Test
	public void testGetOrderById() {
		Optional<Orders> o1 = Optional.ofNullable(new Orders());
		o1.get().setId(1);
		when(orderRepository.findById(1)).thenReturn(o1);
		Optional<Orders> savedOrder = orderService.getOrders(1);
		assertEquals(1, savedOrder.get().getId());
	}

	@Test
	public void testSaveOrder() {
		System.out.println(orderService);
		Orders order = new Orders(); //
		order.setId(1);
		when(orderRepository.save(order)).thenReturn(order);
		Integer orderId = orderService.saveOrder(order);
		assertEquals(1, orderId);
	}

	/*
	 * @Test void testSaveOrder() { System.out.println(orderService);
	 * when(orderService.saveOrder(order)).thenReturn(1);//mock
	 * orderService.saveOrder(order ); }
	 */

}
