package com.example.neo4jexample.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieDto {

	// movie properties
	private Long id;
	private String title;
	private int released;
	private String tagline;
	
	// relationship -> Person 
	private List<PersonDto> directed;
	private List<PersonDto> wrote;
	private List<PersonDto> produced;
	private List<PersonDto> reviewed;
	private List<PersonDto> actedIn;
	
}
