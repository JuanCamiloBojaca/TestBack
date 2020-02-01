package com.example.demo.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.demo.exception.BusNotFoundException;

@ControllerAdvice
public class BusNotFoundAdvice {

	@ResponseBody
	@ExceptionHandler(BusNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String employeeNotFoundHandler(BusNotFoundException ex) {
		return ex.getMessage();
	}

}
