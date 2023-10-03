package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.example.demo.entity.Orders;
import com.example.demo.service.IOrderService;

@SpringBootTest
class OrderControllerTest {
	@InjectMocks
	OrderController orderController;

	@Mock
	IOrderService iOrderService;

	@Test
	public void testGetOrders() {
		List<Orders> orders = new ArrayList<>();
		Orders o1 = new Orders();
		orders.add(o1);
		when(iOrderService.getOrders()).thenReturn(orders);
		List<Orders> result = orderController.getOrders();
		assertEquals(1, result.size());
	}

//	@Test
	void testGetOrdersById() {
		Optional<Orders> order = Optional.of(new Orders());
		when(iOrderService.getOrders(2)).thenReturn(order);
		ResponseEntity<Orders> result = orderController.getOrdersById(2);
		assertEquals(HttpStatus.OK, result.getStatusCode());
	}
	@Test
	void testGetOrdersByIdForNonExistingOrder() {
		when(iOrderService.getOrders(1)).thenThrow(new RuntimeException());
		ResponseEntity<Orders> result = orderController.getOrdersById(1);
		assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
	}

//	@Test
	void testHandleValidationExceptions() throws NoSuchMethodException, SecurityException {
		Method method = OrderController.class.getMethod("createOrder", Orders.class);
		MethodParameter parameter = new MethodParameter(method, 0);
		MethodArgumentNotValidException ex = new MethodArgumentNotValidException(parameter, null);
		Map<String, String> result = orderController.handleValidationExceptions(ex);
		System.out.println(result);
	}

}
