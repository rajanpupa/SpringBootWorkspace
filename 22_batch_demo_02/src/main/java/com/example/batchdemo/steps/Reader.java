package com.example.batchdemo.steps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class Reader implements ItemReader<String>{
	
	private String[] message={"Hello World!", "Welcome to Spring Batch!"};
	private int count=0;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public String read() throws Exception, UnexpectedInputException,
			ParseException, NonTransientResourceException {
		if(count < message.length){
			return message[count++];
		}else{
			count=0;
		}
		return null;
	}

}
