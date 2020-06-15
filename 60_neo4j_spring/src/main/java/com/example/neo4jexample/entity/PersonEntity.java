package com.example.neo4jexample.entity;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.neo4j.ogm.annotation.Id;

@NodeEntity(label="Person")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonEntity {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private int born;

	@Relationship(type = "ACTED_IN")
	private List<MovieEntity> movies;

	public PersonEntity(String name, int born) {
		this.name = name;
		this.born = born;
	}
	
	public void addMovie(MovieEntity movie) {
		if(this.movies == null) {
			this.movies = new ArrayList<MovieEntity>();
		}
		this.movies.add(movie);
	}

}
