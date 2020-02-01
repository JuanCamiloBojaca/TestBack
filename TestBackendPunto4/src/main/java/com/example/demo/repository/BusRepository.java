package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Bus;

public interface BusRepository extends CrudRepository<Bus, Integer> {
	List<Bus> findAll();
}
