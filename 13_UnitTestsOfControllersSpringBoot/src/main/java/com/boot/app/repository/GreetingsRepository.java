package com.boot.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boot.app.model.Greeting;

@Repository
public interface GreetingsRepository extends JpaRepository<Greeting, Long>{

}
