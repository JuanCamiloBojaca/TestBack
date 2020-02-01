package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Device;

public interface DeviceRepository extends CrudRepository<Device, Integer> {
	List<Device> findAll();
}
