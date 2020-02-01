package com.example.demo.exception;

public class DeviceTypeNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DeviceTypeNotFoundException(int id) {
		super(String.format("Device type with id %d not found :(", id));
	}
}
