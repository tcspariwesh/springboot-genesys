package com.example.demo.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Orders;
import com.example.demo.repository.IOrderRepository;

@Service
public class OrderService implements IOrderService {
	@Autowired
	IOrderRepository repository;
	Logger logger = LoggerFactory.getLogger(getClass());

	public OrderService() {
		System.out.println("Order service bean initializing");
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public Integer saveOrder(Orders order) {
		order.setCreatedDate(new Date());
		System.out.println(order.getItem());
		/*
		 * if (1 == 1) try {// never handle any exception in service class throw new
		 * IOException("something went wrong"); } catch (IOException e) {
		 * e.printStackTrace(); // rethrow }
		 */
//		repository.deleteById(1);
		repository.save(order);//mock
		logger.debug("saved successfully");
		// another db operation
		return order.getId();
	}

	@Override
	public List<Orders> getOrders() {
		logger.debug("getting all records");
		return repository.findAll();
	}

	@Override
	public Optional<Orders> getOrders(Integer id) {
		return repository.findById(id);
	}

	@Override
	@Transactional
	public void deleteOrders(Integer id) {
		repository.deleteById(id);
	}

	@Override
	public Orders getOrdersByMobile(String mobile) {
		return repository.findByMobile(mobile);
	}
}
