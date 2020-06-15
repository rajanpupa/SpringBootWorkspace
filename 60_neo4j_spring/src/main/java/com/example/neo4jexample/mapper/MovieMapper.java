package com.example.neo4jexample.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.neo4jexample.dto.MovieDto;
import com.example.neo4jexample.dto.PersonDto;
import com.example.neo4jexample.entity.MovieEntity;
import com.example.neo4jexample.entity.PersonEntity;
import com.example.neo4jexample.entity.RoleEntity;

@Component
public class MovieMapper {

	public MovieDto toDto(MovieEntity movieEntity) {
		MovieDto movieDto = MovieDto.builder()
				.id(movieEntity.getId())
				.title(movieEntity.getTitle())
				.released(movieEntity.getReleased())
				.tagline(movieEntity.getTagline())
				.directed(filterPerson(movieEntity, "DIRECTED"))
				.wrote(filterPerson(movieEntity, "WROTE"))
				.produced(filterPerson(movieEntity, "PRODUCED"))
				.reviewed(filterPerson(movieEntity, "REVIEWED"))
				.actedIn(filterPerson(movieEntity, "ACTED_IN"))
				.build();
		return movieDto;
	}
	public MovieEntity toEntity(MovieDto movieDto) {
		MovieEntity movieEntity = MovieEntity.builder()
				.id(movieDto.getId())
				.title(movieDto.getTitle())
				.released(movieDto.getReleased())
				.tagline(movieDto.getTagline())
				.build()
				;
		movieEntity.setRoles(toRolesEntities(movieDto.getActedIn(),movieEntity));
		return movieEntity;
	}
	
	private List<RoleEntity> toRolesEntities(List<PersonDto> actedIn, MovieEntity movieEntity) {
		List<RoleEntity> roleEntities = new ArrayList();
		
		for(PersonDto person: actedIn) {
			roleEntities.add(
					RoleEntity.builder()
					.person(toPersonEntity(person))
					.build()
			);
		}
		
		return roleEntities;
	}
	private PersonEntity toPersonEntity(PersonDto person) {
		return PersonEntity.builder()
				.id(person.getId())
				.name(person.getName())
				.born(person.getBorn())
				.build();
	}
	// PRIVATE METHODS
	private PersonDto toPersonDto(PersonEntity personEntity) {
		return PersonDto.builder()
				.id(personEntity.getId())
				.name(personEntity.getName())
				.born(personEntity.getBorn())
				.build();
	}
	
	private List<PersonDto> filterPerson(MovieEntity movieEntity, String role) {
		return movieEntity.getRoles().stream()
				.filter(r -> r.getRoles().contains(role))
				.map(r-> toPersonDto(r.getPerson()))
				.collect(Collectors.toList());
	}
}
