package com.tprojectboot.application.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
public class DatabaseConfig {
	//Mybatis는 Spring Boot에서 제공해주지 않으므로 따로 Bean설정이 필요//
	@Value("${spring.datasource.driver-class-name}")
	private String driverclass;
	
	@Value("${spring.datasource.url}")
	private String datasourceUrl;
	
	@Value("${spring.datasource.username}")
	private String username;
	
	@Value("${spring.datasource.password}")
	private String password;
	
    @Bean
    public DataSource dataSource(Environment environment) {
    	BasicDataSource dataSource = new BasicDataSource();
    	
        dataSource.setDriverClassName(driverclass);
        dataSource.setUrl(datasourceUrl);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        
        return dataSource;
    }
    
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource datasource)throws Exception{
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		
		sqlSessionFactory.setDataSource(datasource);
		sqlSessionFactory.setMapperLocations(
				new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/**/*.xml"));
		
		return (SqlSessionFactory)sqlSessionFactory.getObject();
	}
	
	@Bean
	public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory){
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}
