package com.boot.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;


public final class BikashHackerRank {
	
	
	class a{
		void test(){
			if(BikashHackerRank.this.flag){
				sample();
			}
		}
	}
	
	private boolean flag = true;
	
	public void sample(){
		
	}
	
	public static synchronized void main(String[] args) {
		System.out.println(compute("banana"));
		boolean love = "bandana rocks".matches("[a-zA-Z ]{0,}");
		
		System.out.println(love);
	}
	
	static String compute(String s){
		
		Set<String> set = new HashSet<>();
		
		for(int i=0; i<s.length(); i++){
			for(int j=i+1; j<=s.length(); j++){
				set.add(s.substring(i,j));
			}
		}
		
		List<String> list = new ArrayList<>();
		list.addAll(set);
		
		Collections.sort(list);
		
		return list.get(list.size()-1);
		
		
		//return "";
	}
}
