package com.hotsse.vhere.core.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {

	/**
	 * DataSource 객체 생성
	 *
	 * @methodName	dataSource
	 * @return
	 */
	@Bean(name = "dataSource")
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}
	
	/**
	 * SqlSessionFactory 객체 생성
	 * 
	 * @methodName	sqlSessionFactory
	 * @param		dataSource
	 * @param		applicationContext
	 * @return
	 * @throws		Exception
	 */
	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource, ApplicationContext applicationContext) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath*:mybatis/mapper/**/*.xml"));
		sqlSessionFactoryBean.setTypeAliasesPackage("**.vo");
		return sqlSessionFactoryBean.getObject();
	}
	
	/**
	 * SqlSessionTemplate 객체 생성
	 * @methodName	sqlSessionTemplate
	 * @param		sqlSessionFactory
	 * @return
	 * @throws		Exception
	 */
	@Bean(name = "sqlSessionTemplate")
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}
