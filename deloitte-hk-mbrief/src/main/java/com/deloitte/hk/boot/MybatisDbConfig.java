package com.deloitte.hk.boot;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * 数据源相关配置类
 * @author deezhang
 *
 */
@Configuration
@MapperScan(basePackages = {"com.deloitte.hk.mizuho.dao"}, sqlSessionFactoryRef = "sqlSessionFactory")
public class MybatisDbConfig {

	@Autowired
	@Qualifier("ds")
	private DataSource ds;

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(ds);
		return factoryBean.getObject();
//		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
//		factoryBean.setDataSource(ds); 
//        Resource configLocation = new PathMatchingResourcePatternResolver().getResource("classpath:/mybatis/mybatis-config.xml");
//        factoryBean.setConfigLocation(configLocation);
//        return factoryBean.getObject();
	}
	
	@Bean
	public SqlSessionTemplate sqlSessionTemplate() throws Exception {
		SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory()); 
		return template;

	}
	
	@Bean
	public DataSourceTransactionManager transactionManager() {
		 return new DataSourceTransactionManager(ds);
	}
}
