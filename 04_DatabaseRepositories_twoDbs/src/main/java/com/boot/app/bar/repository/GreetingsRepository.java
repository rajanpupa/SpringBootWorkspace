package com.boot.app.bar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boot.app.bar.model.Greeting;

@Repository
public interface GreetingsRepository extends JpaRepository<Greeting, Long>{

	Greeting findById(Long id);
}
