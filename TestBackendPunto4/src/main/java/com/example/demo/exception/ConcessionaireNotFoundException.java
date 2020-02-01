package com.example.demo.exception;

public class ConcessionaireNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ConcessionaireNotFoundException(int id) {
		super(String.format("Concessionaire with id %d not found :(", id));
	}
}
