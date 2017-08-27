package com.boot.app.foo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boot.app.foo.model.Greeting;

@Repository
public interface GreetingsRepository extends JpaRepository<Greeting, Long>{

	Greeting findById(Long id);
}
