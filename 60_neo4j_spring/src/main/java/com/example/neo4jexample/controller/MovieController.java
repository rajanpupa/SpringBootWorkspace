package com.example.neo4jexample.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.neo4jexample.dto.MovieDto;
import com.example.neo4jexample.entity.MovieEntity;
import com.example.neo4jexample.service.MovieService;

@RestController
@RequestMapping("/api/movie/v1")
public class MovieController {

	private final MovieService movieService;
	
	public MovieController(MovieService movieService) {
		this.movieService = movieService;
	}

    @GetMapping("/graph")
	public Map<String, Object> graph(@RequestParam(value = "limit",required = false) Integer limit) {
		return movieService.graph(limit == null ? 100 : limit);
	}
    
    @PostMapping("/save")
    public MovieEntity saveMovie(@RequestBody MovieDto movie) {
    	return movieService.saveMovie(movie);
    }
    
    // create a new movie
    
    // update movie in graph
    
    // delete movie in graph
}