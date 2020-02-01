package com.example.demo.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.BusNotFoundException;
import com.example.demo.exception.DeviceNotFoundException;
import com.example.demo.exception.DeviceTypeNotFoundException;
import com.example.demo.model.Bus;
import com.example.demo.model.Device;
import com.example.demo.model.DeviceType;
import com.example.demo.repository.BusRepository;
import com.example.demo.repository.DeviceRepository;
import com.example.demo.repository.DeviceTypeRepository;

@RestController
public class DeviceController {

	@Autowired
	DeviceRepository repository;

	@Autowired
	DeviceTypeRepository deviceTypeRepository;

	@Autowired
	BusRepository busRepository;

	@GetMapping("/Buses/{id}/Devices")
	Collection<Device> all(@PathVariable int id) {
		return busRepository.findById(id).orElseThrow(() -> new BusNotFoundException(id)).devices();
	}

	@PostMapping("/Buses/{id}/Devices")
	Device newBus(@RequestBody Device newDevice, @PathVariable int id) {
		Bus bus = busRepository.findById(id).orElseThrow(() -> new BusNotFoundException(id));
		newDevice.setBus(bus);
		return repository.save(newDevice);
	}

	@GetMapping("/Buses/{id}/Devices/{id2}")
	Device one(@PathVariable int id, @PathVariable int id2) {
		return getDeviceWithBus(id, id2);
	}

	@PutMapping("/Buses/{id}/Devices/{id2}")
	Device replaceDevice(@RequestBody Device newDevice, @PathVariable int id, @PathVariable int id2) {
		Device device = getDeviceWithBus(id, id2);
		device.setIp(newDevice.getIp());
		device.setStatus(newDevice.getStatus());
		return repository.save(device);
	}

	@DeleteMapping("/Buses/{id}/Devices/{id2}")
	ResponseEntity<?> deleteDevice(@PathVariable int id, @PathVariable int id2) {
		getDeviceWithBus(id, id2);
		repository.deleteById(id2);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/Buses/{id}/Devices/{id2}/DeviceType/{id3}")
	ResponseEntity<?> addDeviceType(@PathVariable int id, @PathVariable int id2, @PathVariable int id3) {
		Device device = getDeviceWithBus(id, id2);
		DeviceType deviceType = deviceTypeRepository.findById(id3)
				.orElseThrow(() -> new DeviceTypeNotFoundException(id3));

		device.setDeviceType(deviceType);
		repository.save(device);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/Buses/{id}/Devices/{id2}/DeviceType")
	ResponseEntity<?> removeDeviceTyep(@PathVariable int id, @PathVariable int id2) {
		Device device = getDeviceWithBus(id, id2);
		device.setDeviceType(null);
		repository.save(device);
		return ResponseEntity.noContent().build();
	}

	Device getDeviceWithBus(int idBus, int iddevice) {
		Bus bus = busRepository.findById(idBus).orElseThrow(() -> new BusNotFoundException(idBus));
		Device device = repository.findById(iddevice).orElseThrow(() -> new DeviceNotFoundException(iddevice));
		if (device.getBus() == bus)
			return device;
		else
			throw new DeviceNotFoundException(iddevice);
	}

}
