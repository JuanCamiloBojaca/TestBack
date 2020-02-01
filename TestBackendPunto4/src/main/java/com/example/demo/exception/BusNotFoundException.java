package com.example.demo.exception;

public class BusNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public BusNotFoundException(int id) {
		super(String.format("Bus with id %d not found :(", id));
	}
}
