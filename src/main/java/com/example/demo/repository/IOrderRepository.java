package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Orders;

public interface IOrderRepository extends JpaRepository<Orders, Integer>{
	Orders findByMobile(String mobile);
	Orders findByMobileAndEmail(String mobile, String email);
}
 