package com.paralucent.config;

import org.springframework.boot.web.error.ErrorPage;
import org.springframework.boot.web.error.ErrorPageRegistrar;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.paralucent.io.servlet.SessionCounterListener;

/**
 * Static resources, error pages and listeners, migrated from spring-config.xml / web.xml.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	registry.addResourceHandler("/libs/**").addResourceLocations("/libs/");
    }

    @Bean
    public ErrorPageRegistrar errorPageRegistrar() {
	return registry -> registry.addErrorPages(
		new ErrorPage(HttpStatus.NOT_FOUND, "/undefined"),
		new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/login"),
		new ErrorPage(HttpStatus.HTTP_VERSION_NOT_SUPPORTED, "/login"));
    }

    @Bean
    public ServletListenerRegistrationBean<SessionCounterListener> sessionCounterListener() {
	return new ServletListenerRegistrationBean<>(new SessionCounterListener());
    }
}
