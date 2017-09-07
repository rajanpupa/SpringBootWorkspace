package com.example.batchdemo;


import java.io.File;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
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
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;

@SpringBootApplication
@EnableBatchProcessing
public class BatchDemoApplication {
	
	public static class Person{
		private int age;
		private String firstName, email;
		
		public Person(){}
		
		public Person(int age, String firstName, String email){
			this.age=age;
			this.firstName=firstName;
			this.email=email;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}
		
		@Override
		public String toString() {
			return String.format("{fname: %s, age: %d, email: %s}", firstName, age, email);
		}
		
	}
	
	@Bean
	FlatFileItemReader<Person> reader(@Value("${input}") Resource in) throws Exception{
		return new FlatFileItemReaderBuilder<Person>()
				.name("reader")
				.resource(in)
				.targetType(Person.class)
				.linesToSkip(1)
				.delimited().delimiter(",").names(new String[]{"firstName","age","email"})
				.build();
	}
	
	@Bean
	JdbcBatchItemWriter<Person> jdbcWriter(DataSource ds){
		return new JdbcBatchItemWriterBuilder<Person>()
				.dataSource(ds)
				.sql("insert into PEOPLE(AGE, FIRST_NAME, EMAIL) values (:age, :firstName, :email)")
				.beanMapped()
				.build();
	}
	
	@Bean
	Job job (	JobBuilderFactory jbf, 
				StepBuilderFactory sbf,
				ItemReader<? extends Person> ir,
				ItemWriter<? super Person>iw
			){
		
		Step s1 = sbf.get("file-db")
				.<Person,Person> chunk(10)			// read person, produce person
				.reader(ir)
				.processor(new ItemProcessor<Person, Person>() {

					@Override
					public Person process(Person arg0) throws Exception {
						System.out.println("Processing: " + arg0);
						arg0.setAge(arg0.getAge()+100);
						return arg0;
					}
				})
				.writer(iw)
				.build();
		
		return jbf.get("etl")					// job name
				.incrementer(new RunIdIncrementer()) // id of the job
				.start(s1)
				.build()
				;
		
	}

	public static void main(String[] args) {
		
		System.setProperty("input", "file:///" +  new File("in.csv").getAbsolutePath());
		
		System.out.println("input = "+ System.getProperty("input"));
		
		SpringApplication.run(BatchDemoApplication.class, args);
	}
	
}
