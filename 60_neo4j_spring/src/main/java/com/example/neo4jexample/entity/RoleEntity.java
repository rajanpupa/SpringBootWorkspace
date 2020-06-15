package com.example.neo4jexample.entity;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.neo4j.ogm.annotation.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RelationshipEntity(type = "ACTED_IN")
public class RoleEntity {
    @Id
    @GeneratedValue
	private Long id;
    private List<String> roles;
    
	@StartNode
	private PersonEntity person;
	@EndNode
	private MovieEntity movie;
	
	public RoleEntity(MovieEntity movie, PersonEntity actor) {
		this.movie = movie;
		this.person = actor;
	}

    public void addRoleName(String name) {
        if (this.roles == null) {
            this.roles = new ArrayList<>();
        }
        this.roles.add(name);
    }
}
