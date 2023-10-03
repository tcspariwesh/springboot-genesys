package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
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
	void getOrdersByMobile() {
		Orders o1 = new Orders();
		String mobileNumber = "123456789";
		o1.setMobile(mobileNumber );
		when(orderRepository.findByMobile(mobileNumber)).thenReturn(o1);
		Orders result = orderService.getOrdersByMobile(mobileNumber);
		assertNotNull(result);
		assertEquals(mobileNumber, result.getMobile());
	}
	@Test
	public void testGetOrderById() {
		Optional<Orders> o1 = Optional.ofNullable(new Orders());
		o1.get().setId(1);
		when(orderRepository.findById(1)).thenReturn(o1);
		Optional<Orders> savedOrder = orderService.getOrders(1);
		assertEquals(1, savedOrder.get().getId());
	}
	@Test//negative test
	public void testGetOrderByNonExistingId() {
		Optional<Orders> o1 = Optional.ofNullable(new Orders());
		o1.get().setId(1);
		when(orderRepository.findById(0)).thenReturn(null);
		Optional<Orders> savedOrder = orderService.getOrders(0);
//		assertEquals(true,savedOrder.isEmpty());
		assertNull(savedOrder);
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
