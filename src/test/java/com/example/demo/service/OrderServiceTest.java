package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.entity.Orders;

@SpringBootTest
//@RunWith(SpringRunner.class)
class OrderServiceTest {
	@MockBean
	OrderService service;

	@Test
	void testSaveOrder() {
		List<Orders> orders = new ArrayList<>();
		Orders o1 = new Orders();
		orders.add(o1);
		o1.setEmail("pari@gmail.com");
		Mockito.when(service.getOrders()).thenReturn(orders );
		List<Orders> result = service.getOrders();
		assertEquals(1, result.size());
	}

}
