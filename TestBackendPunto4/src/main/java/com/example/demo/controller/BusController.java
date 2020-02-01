package com.example.demo.controller;

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

import com.example.demo.exception.BusNotFoundException;
import com.example.demo.exception.ConcessionaireNotFoundException;
import com.example.demo.model.Bus;
import com.example.demo.model.Concessionaire;
import com.example.demo.repository.BusRepository;
import com.example.demo.repository.ConcessionaireRepository;

@RestController
public class BusController {

	@Autowired
	BusRepository repository;

	@Autowired
	ConcessionaireRepository concessionaireRepository;

	@GetMapping("/Buses")
	List<Bus> all() {
		return repository.findAll();
	}

	@PostMapping("/Buses")
	Bus newBus(@RequestBody Bus newBus) {
		return repository.save(newBus);
	}

	@GetMapping("/Buses/{id}")
	Bus one(@PathVariable int id) {
		return repository.findById(id).orElseThrow(() -> new BusNotFoundException(id));
	}

	@PutMapping("/Buses/{id}")
	Bus replaceBus(@RequestBody Bus newBus, @PathVariable int id) {
		return repository.findById(id).map(bus -> {
			bus.setBrakes(newBus.getBrakes());
			bus.setMotor(newBus.getMotor());
			bus.setType(bus.getType());
			return repository.save(bus);
		}).orElseGet(() -> repository.save(newBus));
	}

	@DeleteMapping("/Buses/{id}")
	ResponseEntity<?> deleteBus(@PathVariable int id) {
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("Buses/{idBus}/Concessionaires/{id}")
	ResponseEntity<?> addBusToConcessionaire( @PathVariable int idBus, @PathVariable int id) {
		Bus bus = repository.findById(idBus).orElseThrow(() -> new BusNotFoundException(id));
		Concessionaire concessionaire = concessionaireRepository.findById(id)
				.orElseThrow(() -> new ConcessionaireNotFoundException(id));
		bus.setConcessionaire(concessionaire);
		repository.save(bus);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("Buses/{id}/Concessionaires")
	ResponseEntity<?> removeBusofConcessionaire(@PathVariable int id) {
		repository.findById(id).ifPresent(bus -> {
			bus.setConcessionaire(null);
			repository.save(bus);
		});
		return ResponseEntity.noContent().build();
	}
}
