package com.example.demo.exception;

public class DeviceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DeviceNotFoundException(int id) {
		super(String.format("Device with id %d not found :(", id));
	}
}
