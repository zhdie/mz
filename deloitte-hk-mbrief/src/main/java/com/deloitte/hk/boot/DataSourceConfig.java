package com.deloitte.hk.boot;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {
	
	@Bean(name = "ds")
	@ConfigurationProperties(prefix = "spring.datasource")
//	@Primary
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
//		DataSource datasource = DataSourceBuilder.create().build();
//		datasource.setTestOnBorrow(true);
//		datasource.setValidationQuery("select 1");		
//		datasource.setMinIdle(5);
//		datasource.setInitialSize(5);
//		datasource.setValidationInterval(30000);		
//		datasource.setTimeBetweenEvictionRunsMillis(30000);
//		datasource.setMinEvictableIdleTimeMillis(30000);
//		return datasource;
	}
}
