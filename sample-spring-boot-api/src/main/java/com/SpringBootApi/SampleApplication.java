package com.SpringBootApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class,
		HibernateJpaAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class, SecurityAutoConfiguration.class })
@Configuration 
@ComponentScan
public class SampleApplication extends SpringBootServletInitializer {
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SampleApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(SampleApplication.class, args);
	}
}
