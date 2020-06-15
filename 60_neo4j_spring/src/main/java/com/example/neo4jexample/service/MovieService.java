package com.example.neo4jexample.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.neo4jexample.dto.MovieDto;
import com.example.neo4jexample.dto.PersonDto;
import com.example.neo4jexample.entity.MovieEntity;
import com.example.neo4jexample.entity.PersonEntity;
import com.example.neo4jexample.entity.RoleEntity;
import com.example.neo4jexample.mapper.MovieMapper;
import com.example.neo4jexample.repository.MovieRepository;
import com.example.neo4jexample.repository.PersonRepository;

@Service
public class MovieService {
    private final static Logger LOG = LoggerFactory.getLogger(MovieService.class);

	private MovieRepository movieRepository;
	private PersonRepository personRepository;
	private MovieMapper movieMapper;

	public MovieService(
			PersonRepository personRepository,
			MovieRepository movieRepository,
			MovieMapper movieMapper
	){
		this.personRepository = personRepository;
		this.movieRepository = movieRepository;
		this.movieMapper = movieMapper;
	}

	private Map<String, Object> toD3Format(Collection<MovieEntity> movies) {
		List<Map<String, Object>> nodes = new ArrayList<>();
		List<Map<String, Object>> rels = new ArrayList<>();
		int i = 0;
		Iterator<MovieEntity> result = movies.iterator();
		while (result.hasNext()) {
			MovieEntity movie = result.next();
			nodes.add(map("title", movie.getTitle(), "label", "movie"));
			int target = i;
			i++;
			for (RoleEntity role : movie.getRoles()) {
				Map<String, Object> actor = map("title", role.getPerson().getName(), "label", "actor");
				int source = nodes.indexOf(actor);
				if (source == -1) {
					nodes.add(actor);
					source = i++;
				}
				rels.add(map("source", source, "target", target));
			}
		}
		return map("nodes", nodes, "links", rels);
	}

	private Map<String, Object> map(String key1, Object value1, String key2, Object value2) {
		Map<String, Object> result = new HashMap<String, Object>(2);
		result.put(key1, value1);
		result.put(key2, value2);
		return result;
	}

    @Transactional(readOnly = true)
    public MovieEntity findByTitle(String title) {
        MovieEntity result = movieRepository.findByTitle(title);
        return result;
    }

    @Transactional(readOnly = true)
    public Collection<MovieEntity> findByTitleLike(String title) {
        Collection<MovieEntity> result = movieRepository.findByTitleLike(title);
        return result;
    }

	@Transactional(readOnly = true)
	public Map<String, Object>  graph(int limit) {
		Collection<MovieEntity> result = movieRepository.graph(limit);
		return toD3Format(result);
	}

	public MovieEntity saveMovie(MovieDto movie) {
		// save
		MovieEntity movieEntity = movieRepository.mergeSave(
				movie.getTitle(), 
				movie.getReleased(), 
				movie.getTagline()
		);
		
		List<PersonEntity> actorsList = new ArrayList<>();
		
		if(movieEntity.getRoles() == null) {
			movieEntity.setRoles(new ArrayList<RoleEntity>());
		}
		List<RoleEntity> roles = movieEntity.getRoles();
		
		for(PersonDto person: movie.getActedIn()) {
			PersonEntity personEntity = personRepository.mergeSave(person.getName(), person.getBorn());
			personEntity.addMovie(movieEntity);
			
			roles.add(
					RoleEntity.builder()
					.movie(movieEntity)
					.person(personEntity)
					.roles(Arrays.asList("ACTED_IN"))
					.build()
					);
			actorsList.add(personEntity);
		}
		
		movieEntity.setRoles(roles);
		
		movieRepository.save(movieEntity);
		
		return movieEntity;
	}
}
