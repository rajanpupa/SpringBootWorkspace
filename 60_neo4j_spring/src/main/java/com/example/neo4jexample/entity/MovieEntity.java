package com.example.neo4jexample.entity;

import java.util.List;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.neo4j.ogm.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@NodeEntity(label="Movie")
public class MovieEntity {
	@Id
    @GeneratedValue
    private Long id;
    private String title;
    private int released;
    private String tagline;

    @Relationship(type = "ACTED_IN", direction = Relationship.INCOMING)
    @JsonIgnore
    private List<RoleEntity> roles;
}
