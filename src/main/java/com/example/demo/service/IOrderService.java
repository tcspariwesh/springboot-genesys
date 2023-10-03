package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Orders;

public interface IOrderService {
	Integer saveOrder(Orders order);

	List<Orders> getOrders();

	Optional<Orders> getOrders(Integer id);

	void deleteOrders(Integer id);

	Orders getOrdersByMobile(String mobile);
}
