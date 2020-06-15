package com.example.neo4jexample.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.neo4jexample.entity.PersonEntity;

@Repository
public interface PersonRepository extends Neo4jRepository<PersonEntity, Long> {

    PersonEntity findByName(String name);
    
    @Query("Merge(p: Person {name: $name, born: $born}) return p")
    PersonEntity mergeSave(
    		@Param("name") String name,
    		@Param("born") int born
    );

}
