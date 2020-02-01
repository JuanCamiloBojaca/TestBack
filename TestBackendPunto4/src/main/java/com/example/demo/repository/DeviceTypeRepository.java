package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.DeviceType;

public interface DeviceTypeRepository extends CrudRepository<DeviceType, Integer> {
	List<DeviceType> findByName(String Name);

	List<DeviceType> findAll();
}