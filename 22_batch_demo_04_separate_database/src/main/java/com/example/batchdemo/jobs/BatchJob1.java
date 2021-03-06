package com.example.batchdemo.jobs;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;

import com.example.batchdemo.configs.BatchConfiguration;
import com.example.batchdemo.models.Person;


@Configuration
public class BatchJob1{
	
	@Autowired
	StepBuilderFactory sbf;
	
	@Autowired
	JobBuilderFactory jbf;

	@Bean("reader1")
	@Primary
	FlatFileItemReader<Person> reader1(@Value("${input1}") Resource in) throws Exception{
		return new FlatFileItemReaderBuilder<Person>()
				.name("reader")
				.resource(in)
				.targetType(Person.class)
				.linesToSkip(1)
				.delimited().delimiter(",").names(new String[]{"firstName","age","email"})
				.build();
	}
	
	@Primary
	@Bean("writer1")
	JdbcBatchItemWriter<Person> jdbcWriter1(@Qualifier("mainDataSource")DataSource ds){
		return new JdbcBatchItemWriterBuilder<Person>()
				.dataSource(ds)
				.sql("insert into PEOPLE(AGE, FIRST_NAME, EMAIL) values (:age, :firstName, :email)")
				.beanMapped()
				.build();
	}
	
	@Bean("step11")
	Step step11(ItemReader<? extends Person> reader1, ItemWriter<? super Person> writer1){
		Step s1 = sbf.get("file-db1")
				.<Person,Person> chunk(1000)			// read person, produce person
				.reader(reader1)
				.processor(new ItemProcessor<Person, Person>() {
					@Override
					public Person process(Person arg0) throws Exception {
						
						//Thread.sleep(500);
						System.out.println("Processing1: " + arg0);
						arg0.setAge(arg0.getAge()+100);
						return arg0;
					}
				})
				.writer(writer1)
				.build();
		
		return s1;
	}
	
	@Bean("job1")
	Job job( ){
		
		return jbf.get("etl1")					// job name
				.incrementer(new RunIdIncrementer()) // id of the job
				.start( step11(null, null) )
				.build()
				;
	}
}
