package com.example.spring_retry_demo.exception;

public class ProcessingException extends Exception {
	public ProcessingException() {
	}
	
	public ProcessingException(String error) {
		super(error);
	}
}
