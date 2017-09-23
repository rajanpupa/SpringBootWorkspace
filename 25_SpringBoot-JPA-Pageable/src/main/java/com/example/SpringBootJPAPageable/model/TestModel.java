package com.example.SpringBootJPAPageable.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TestModel {

	@Id
	Integer id;
	
	String text;
	
	public TestModel(){}
	public TestModel(Integer id, String text){
		this.id = id;
		this.text = text;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
}
