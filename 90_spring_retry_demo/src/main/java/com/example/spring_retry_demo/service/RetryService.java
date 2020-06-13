package com.example.spring_retry_demo.service;

import java.util.Random;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import com.example.spring_retry_demo.exception.ProcessingException;

@Service
public class RetryService {
	
	Random random = new Random();

	@Retryable(
            value = {ProcessingException.class},
            maxAttempts = 2,
            backoff = @Backoff(delay=1000)
    )
	public String retry() throws ProcessingException {
		int i = random.nextInt(10);
		if( i % 2 == 0 ) {
			System.out.println("Throwing exception for " + i);
			throw new ProcessingException("Random error");
		}else {
			System.out.println("Returning " + i);
			return String.valueOf(i);
		}
	}
	
	/**
	 * if @Retriable method failed multiple times 
	 * then this method is called - if exist
	 */
	@Recover
	public String recover() {
		return "101";
	}
}
