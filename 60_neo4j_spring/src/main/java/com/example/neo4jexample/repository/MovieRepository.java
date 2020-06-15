package com.example.neo4jexample.repository;

import java.util.Collection;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.neo4jexample.entity.MovieEntity;

@Repository
public interface MovieRepository extends Neo4jRepository<MovieEntity, Long> {
	
	public MovieEntity findByTitle(String title);

    public Collection<MovieEntity> findByTitleLike(@Param("title")String title);

    @Query("MATCH (m:Movie)<-[r:ACTED_IN]-(a:Person) RETURN m,r,a LIMIT $limit")
    public Collection<MovieEntity> graph(@Param("limit") int limit);
    
    // save
    @Query("Merge(m: Movie {title: $title, released: $released, tagline: $tagline}) "
    		+ " ON CREATE SET m.roles = []"
    		+ " return m")
    public MovieEntity mergeSave(
    		@Param("title") String title,
    		@Param("released") int released,
    		@Param("tagline") String tagline
    );
}
