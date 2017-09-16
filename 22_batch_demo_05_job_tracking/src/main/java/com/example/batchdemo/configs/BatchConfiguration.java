package com.example.batchdemo.configs;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobOperator;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

@Configuration
@EnableBatchProcessing
@Import({ DatasourceConfigs.class})
public class BatchConfiguration extends DefaultBatchConfigurer{

	protected final static Logger log = LoggerFactory.getLogger(BatchConfiguration.class);
	
	@Autowired
	JobRepository jobRepository;
	@Autowired
	JobRegistry jobRegistry;
	@Autowired 
	JobExplorer jobExplorer;
	
	/*
	 * Configuring bean joblauncher bean
	 * Setting the jobRepository bean, and AsyncTaskExecutor
	 */
	@Bean
	public JobLauncher jobLauncher(){
		final SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
		jobLauncher.setJobRepository(jobRepository);
		
		final SimpleAsyncTaskExecutor simpleAsyncTaskExecutor = new SimpleAsyncTaskExecutor();
		jobLauncher.setTaskExecutor(simpleAsyncTaskExecutor);
		
		return jobLauncher;
	}
	
	@Bean
	public JobOperator jobOperator(){
		
		SimpleJobOperator jo = new SimpleJobOperator();
		
		jo.setJobExplorer(jobExplorer);
		jo.setJobLauncher(jobLauncher());
		jo.setJobRegistry(jobRegistry);
		jo.setJobRepository(jobRepository);
		
		return jo;
	}
	
	// can overwrite the setDataSource(Datasource) 
	@Override
    @Autowired
    public void setDataSource(@Qualifier("batchDataSource") DataSource batchDataSource) {
        super.setDataSource(batchDataSource);
        System.out.println("----Set batch datasource");
    }
	
	
}
