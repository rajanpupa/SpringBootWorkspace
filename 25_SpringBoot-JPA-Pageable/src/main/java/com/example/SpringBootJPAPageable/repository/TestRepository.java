package com.example.SpringBootJPAPageable.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import com.example.SpringBootJPAPageable.model.TestModel;

@Repository
public interface TestRepository extends JpaRepository<TestModel, Integer>, QueryByExampleExecutor<TestModel>{

}
