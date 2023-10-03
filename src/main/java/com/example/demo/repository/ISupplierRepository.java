package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Supplier;

public interface ISupplierRepository extends JpaRepository<Supplier, Integer>{

}
