package com.example.demo.controller;

import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.DeviceTypeNotFoundException;
import com.example.demo.model.Device;
import com.example.demo.model.DeviceType;
import com.example.demo.repository.DeviceTypeRepository;

@RestController
public class DeviceTypeController {

	@Autowired
	DeviceTypeRepository repository;

	@GetMapping("/DeviceTypes")
	List<DeviceType> all() {
		return repository.findAll();
	}

	@PostMapping("/DeviceTypes")
	DeviceType newDeviceType(@RequestBody DeviceType newDeviceType) {
		return repository.save(newDeviceType);
	}

	@GetMapping("/DeviceTypes/{id}")
	DeviceType one(@PathVariable int id) {
		return repository.findById(id).orElseThrow(() -> new DeviceTypeNotFoundException(id));
	}

	@GetMapping("/DeviceTypes/{id}/Devices")
	Collection<Device> getDevices(@PathVariable int id) {
		return repository.findById(id).orElseThrow(() -> new DeviceTypeNotFoundException(id)).devices();
	}

	@PutMapping("/DeviceTypes/{id}")
	DeviceType replaceDeviceType(@RequestBody DeviceType newDeviceType, @PathVariable int id) {
		return repository.findById(id).map(deviceType -> {
			deviceType.setName(newDeviceType.getName());
			return repository.save(deviceType);
		}).orElseGet(() -> repository.save(newDeviceType));
	}

	@DeleteMapping("/DeviceTypes/{id}")
	ResponseEntity<?> deleteDeviceType(@PathVariable int id) {
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
