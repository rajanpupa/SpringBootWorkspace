package com.boot.app;

public class HemBroInterview {
	
	public static void main(String[] args) {
		int [] arr = {4, 3, 1,2, 4};
		int swap = minumumSwaps(arr);
		System.out.println(swap);
	}
	
	// return min swaps to make it descending
	static int minumumSwaps(int[] popularity){
		
		int swap = 0;
		
		boolean retry = true;
		int max = popularity[0];
		int maxind = 0;
		for(int i=0; i<popularity.length-1; i++){
			max = popularity[i+1];
			maxind = i+1;
			// find the greatest value
			for(int j=i+1; j<popularity.length; j++){
				if(max < popularity[j]){
					max = popularity[j];
					maxind = j;
				}
			}
			if(popularity[i]<max){
				swap++;
				int tmp = popularity[i];
				popularity[i] = popularity[maxind];
				popularity[maxind] = tmp;
			}
		}
		
		
		return swap;
	}

}
