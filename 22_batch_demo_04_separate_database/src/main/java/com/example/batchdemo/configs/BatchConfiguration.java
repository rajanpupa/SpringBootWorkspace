package com.example.batchdemo.configs;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableBatchProcessing
@Import({ DatasourceConfigs.class})
public class BatchConfiguration extends DefaultBatchConfigurer{

	protected final static Logger log = LoggerFactory.getLogger(BatchConfiguration.class);
	
	// can overwrite the setDataSource(Datasource) 
	@Override
    @Autowired
    public void setDataSource(@Qualifier("batchDataSource") DataSource batchDataSource) {
        super.setDataSource(batchDataSource);
        System.out.println("----Set batch datasource");
    }
}
