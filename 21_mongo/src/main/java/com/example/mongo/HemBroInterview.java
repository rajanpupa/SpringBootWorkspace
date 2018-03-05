package com.example.mongo;

public class HemBroInterview {
	
	public static void main(String[] args) {
		int [] arr = {4, 3, 1,2, 4};
		int swap = minumumSwaps(arr);
		System.out.println(swap);
	}
	
	// return min swaps to make it descending
	static int minumumSwaps(int[] popularity){
		
		//popularity = arr;
		int swap = 0;
		
		boolean retry = true;
		//int i=0;
		while(retry){
			retry = false;
			for(int i=0; i<popularity.length+1; i++){
				if(popularity[i] <popularity[i+1]){
					retry = true;
					swap++;
					int temp = popularity[i];
					popularity[i]=popularity[i+1];
					popularity[i+1] = temp;
					break;
				}
			}
			
			
		}
		
		
		return swaps;
	}

}
