package com.paralucent.config;

import java.sql.SQLException;

import org.springframework.boot.jdbc.autoconfigure.DataSourceProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.jakarta.StatViewServlet;
import com.alibaba.druid.support.jakarta.WebStatFilter;

/**
 * Druid connection pool and monitor, migrated from spring-config.xml / web.xml.
 */
@Configuration
public class DataSourceConfig {

    @Bean(initMethod = "init", destroyMethod = "close")
    public DruidDataSource dataSource(DataSourceProperties properties) throws SQLException {
	DruidDataSource dataSource = new DruidDataSource();
	dataSource.setUrl(properties.getUrl());
	dataSource.setUsername(properties.getUsername());
	dataSource.setPassword(properties.getPassword());

	dataSource.setInitialSize(0);
	dataSource.setMinIdle(0);
	dataSource.setMaxActive(55);
	dataSource.setMaxWait(5000);
	dataSource.setTimeBetweenEvictionRunsMillis(100);
	dataSource.setMinEvictableIdleTimeMillis(10000);

	dataSource.setValidationQuery("SELECT 1 FROM DUAL");
	dataSource.setTestWhileIdle(true);
	dataSource.setTestOnBorrow(false);
	dataSource.setTestOnReturn(false);

	dataSource.setPoolPreparedStatements(true);
	dataSource.setMaxPoolPreparedStatementPerConnectionSize(200);

	dataSource.setFilters("wall,slf4j,stat");
	dataSource.setDefaultAutoCommit(false);
	return dataSource;
    }

    @Bean
    public ServletRegistrationBean<StatViewServlet> druidStatViewServlet() {
	return new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
    }

    @Bean
    public FilterRegistrationBean<WebStatFilter> druidWebStatFilter() {
	FilterRegistrationBean<WebStatFilter> registration = new FilterRegistrationBean<>(new WebStatFilter());
	registration.addUrlPatterns("/*");
	registration.addInitParameter("exclusions", "/static/*,*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
	return registration;
    }
}
