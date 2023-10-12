package com.example.demo.controller;

import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.demo.entity.Orders;
import com.example.demo.service.OrderService;
@WebMvcTest(controllers = {OrderController.class})
class OrderControllerWebTest {
	@MockBean
	OrderService orderService;
	@Autowired
	MockMvc mockMvc; //client
	@Autowired
	ApplicationContext webApplicationContext;
	@Test
	void testCreateOrder() throws Exception {
		Orders order = new Orders();
		when(orderService.saveOrder(order )).thenReturn(1);
		mockMvc.perform(MockMvcRequestBuilders.post("/order")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\r\n"
						+ "    \"item\": \"iPhone\",\r\n"
						+ "    \"price\":1000,\r\n"
						+ "    \"mobile\":\"2342333333\",\r\n"
						+ "    \"email\":\"pari@gmail.com\",\r\n"
						+ "    \"address\":{\r\n"
						+ "        \"pin\":123456,\r\n"
						+ "        \"house\":\" Prestige\"\r\n"
						+ "    }\r\n"
						+ "}"))
		.andExpect(MockMvcResultMatchers.status().isCreated());
	}
	@Test
	void testCreateOrderWithoutItemName() throws Exception {
		Orders order = new Orders();
		when(orderService.saveOrder(order )).thenReturn(1);
		mockMvc.perform(MockMvcRequestBuilders.post("/order")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{}"))
		.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	@Test
	void testGetOrder() throws Exception {
		Optional<Orders> order = Optional.of(new Orders());
		when(orderService.getOrders(1 )).thenReturn(order);
		mockMvc.perform(MockMvcRequestBuilders.get("/order/1"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
	}

}
