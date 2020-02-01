package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Concessionaire;

public interface ConcessionaireRepository extends CrudRepository<Concessionaire, Integer> {
	List<Concessionaire> findByName(String Name);

	List<Concessionaire> findAll();
}
