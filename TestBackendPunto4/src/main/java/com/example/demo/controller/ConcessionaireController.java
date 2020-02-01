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

import com.example.demo.exception.ConcessionaireNotFoundException;
import com.example.demo.model.Bus;
import com.example.demo.model.Concessionaire;
import com.example.demo.repository.ConcessionaireRepository;

@RestController
public class ConcessionaireController {

	@Autowired
	ConcessionaireRepository repository;

	@GetMapping("/Concessionaires")
	List<Concessionaire> all() {
		return repository.findAll();
	}

	@PostMapping("/Concessionaires")
	Concessionaire newConcessionaire(@RequestBody Concessionaire newConcessionaire) {
		return repository.save(newConcessionaire);
	}

	@GetMapping("/Concessionaires/{id}")
	Concessionaire one(@PathVariable int id) {
		return repository.findById(id).orElseThrow(() -> new ConcessionaireNotFoundException(id));
	}

	@GetMapping("/Concessionaires/{id}/Buses")
	Collection<Bus> getBusses(@PathVariable int id) {
		return repository.findById(id).orElseThrow(() -> new ConcessionaireNotFoundException(id)).buses();
	}

	@PutMapping("/Concessionaires/{id}")
	Concessionaire replaceConcessionaire(@RequestBody Concessionaire newConcessionaire, @PathVariable int id) {
		return repository.findById(id).map(concessionaire -> {
			concessionaire.setName(newConcessionaire.getName());
			return repository.save(concessionaire);
		}).orElseGet(() -> repository.save(newConcessionaire));
	}

	@DeleteMapping("/Concessionaires/{id}")
	ResponseEntity<?> deleteConcessionaire(@PathVariable int id) {
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
