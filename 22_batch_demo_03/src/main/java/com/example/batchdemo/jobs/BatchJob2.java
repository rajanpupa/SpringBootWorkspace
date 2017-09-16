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
import org.springframework.core.io.Resource;

import com.example.batchdemo.models.Person;


@Configuration
public class BatchJob2 {
	
	@Autowired
	StepBuilderFactory sbf;
	
	@Autowired
	JobBuilderFactory jbf ;

	@Bean("reader2")
	FlatFileItemReader<Person> reader2(@Value("${input2}") Resource in) throws Exception{
		return new FlatFileItemReaderBuilder<Person>()
				.name("reader2")
				.resource(in)
				.targetType(Person.class)
				.linesToSkip(1)
				.delimited().delimiter(",").names(new String[]{"firstName","age","email"})
				.build();
	}
	
	@Bean("writer2")
	JdbcBatchItemWriter<Person> jdbcWriter(DataSource ds){
		return new JdbcBatchItemWriterBuilder<Person>()
				.dataSource(ds)
				.sql("insert into PEOPLE(AGE, FIRST_NAME, EMAIL) values (:age, :firstName, :email)")
				.beanMapped()
				.build();
	}
	
	@Bean("step21")
	Step step1( @Qualifier("reader2") ItemReader<? extends Person> reader2,
			 @Qualifier("writer2") ItemWriter<? super Person> writer2){
		Step s1 = sbf.get("file-db2")
				.<Person,Person> chunk(10)			// read person, produce person
				.reader(reader2)
				.processor(new ItemProcessor<Person, Person>() {

					@Override
					public Person process(Person arg0) throws Exception {
						System.out.println("Processing: " + arg0);
						arg0.setAge(arg0.getAge()+100);
						return arg0;
					}
				})
				.writer(writer2)
				.build();
		
		return s1;
	}
	
	@Bean("job2")
	Job job( ){
		
		return jbf.get("etl2")					// job name
				.incrementer(new RunIdIncrementer()) // id of the job
				.start( step1(null, null) )
				//.next(step2)
				.build()
				;
		
	}
}
