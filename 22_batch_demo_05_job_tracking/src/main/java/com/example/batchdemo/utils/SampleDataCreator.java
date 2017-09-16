package com.example.batchdemo.utils;

public class SampleDataCreator {

	public static void main(String[] args) {
		for(int i=0; i<10000; i++){
			System.out.printf("job2, %d, some sample data %d\n", i, i);
		}
	}
}
